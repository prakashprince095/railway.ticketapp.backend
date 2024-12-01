package com.sparx.railway.ticketapp.backend.serviceimpl;

import com.sparx.railway.ticketapp.backend.Exception.ResourceNotFoundException;
import com.sparx.railway.ticketapp.backend.constant.Constant;
import com.sparx.railway.ticketapp.backend.dto.GetTicketByPnrNoDTO;
import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import com.sparx.railway.ticketapp.backend.dto.TicketBookingDTO;
import com.sparx.railway.ticketapp.backend.entities.PassangerDetailEntity;
import com.sparx.railway.ticketapp.backend.entities.SeatToPassangerMappingEntity;
import com.sparx.railway.ticketapp.backend.entities.TicketEntity;
import com.sparx.railway.ticketapp.backend.entities.TicketStatusEntity;
import com.sparx.railway.ticketapp.backend.enums.TicketStatus;
import com.sparx.railway.ticketapp.backend.repository.*;
import com.sparx.railway.ticketapp.backend.service.TicketBookingService;
import com.sparx.railway.ticketapp.backend.util.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketBookingServiceImpl implements TicketBookingService {
    private  final PassangerDetailEntityRepository passangerDetailEntityRepository;
    private final TokenUtil tokenUtil;
    private  final TicketEntityRepository ticketEntityRepository;
    private final ModelMapper modelMapper;
    private final PNRGenratorUtil pnrGenratorUtil;
    private final TicketPriceUtil ticketPriceUtil;
    private final StationEntityRepository stationEntityRepository;
    private final TicketStatusEntityRepository ticketStatusEntityRepository;
    private final TimeandDateUtil timeandDateUtil;
    private final SeatToPassangerMappingEntityRepository seatToPassangerMappingEntityRepository;
    private static final Logger logger =  LoggerFactory.getLogger(TicketBookingServiceImpl.class);
    private final EmailUtil emailUtil;

    @Override
    public ServiceResponseDTO bookTicket(HttpServletRequest request, TicketBookingDTO ticketBookingDTO) {
        try{
            String userEmail=tokenUtil.getUserNameFromTheToken(request.getHeader("Authorization"));
            int startSationDistance=stationEntityRepository.findByStationName(ticketBookingDTO.getJourneyStartStation()).get().getDistance();
            int endStationDistance=stationEntityRepository.findByStationName(ticketBookingDTO.getJourneyEndStation()).get().getDistance();
            int totalDistance=Math.abs(startSationDistance-endStationDistance);
            int  trainNo=ticketBookingDTO.getTrainNo();
            String pnrNo= String.valueOf(pnrGenratorUtil.buildPNRNO(trainNo));
            System.out.println("the current time stamp is "+ Instant.now());
            List<TicketBookingDTO.PassangerDetail> pasangerDetailList =ticketBookingDTO.getPassangerDetails();
            List<PassangerDetailEntity> passangerDetailEntitiesList=new ArrayList<PassangerDetailEntity>();
            List<Integer> passangerAgeList=new ArrayList<>();
            Optional<TicketStatusEntity> ticketStatusEntity= Optional.ofNullable(ticketStatusEntityRepository.findTicketStatusEntityByTrainNoAndDate
                            (trainNo, timeandDateUtil.setTravelDate(ticketBookingDTO.getStartDateOfJourney()))
                    .orElseThrow(() -> new ResourceNotFoundException
                            (HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK.value(),
                                    "ticket status entity could not be found with the requested details ")));
            List<SeatToPassangerMappingEntity> seatToPassangerMappingEntityList=new ArrayList<SeatToPassangerMappingEntity>();
            int count =0;
            for(TicketBookingDTO.PassangerDetail pasangerDetail : pasangerDetailList){
                List<String> coachAndSeatNoList=alloatTheSeatNoAndCoachNo(ticketStatusEntity,ticketBookingDTO.getCoachType());
                int seatCount= Integer.parseInt(coachAndSeatNoList.get(1))+count++;
                PassangerDetailEntity passangerDetailEntity=PassangerDetailEntity.builder()
                        .passangerName(pasangerDetail.getPassangerName())
                        .age(pasangerDetail.getAge())
                        .gender(pasangerDetail.getGender())
                        .contactNumber(pasangerDetail.getContactNumber())
                        .emailId(pasangerDetail.getEmailId())
                        .dateOfBirth(pasangerDetail.getDateOfBirth())
                        .coachNo(coachAndSeatNoList.get(0))
                        .seatNo(String.valueOf(seatCount))
                        .pnrNo(pnrNo).build();
                passangerDetailEntitiesList.add(passangerDetailEntity);
                passangerAgeList.add(pasangerDetail.getAge());
                SeatToPassangerMappingEntity seatToPassangerMappingEntity1=SeatToPassangerMappingEntity.builder()
                        .seatNumber(String.valueOf(seatCount))
                        .coachNo(coachAndSeatNoList.get(0))
                        .pnrNo(pnrNo)
                        .trainNo(ticketBookingDTO.getTrainNo())
                        .dateOfTravel(timeandDateUtil.setTravelDate(ticketBookingDTO.getStartDateOfJourney()))
                        .build();
                seatToPassangerMappingEntityList.add(seatToPassangerMappingEntity1);
            }
            List<SeatToPassangerMappingEntity> savedSeatToPassangerMappingList=seatToPassangerMappingEntityRepository.saveAll(seatToPassangerMappingEntityList);
            logger.info("seat to passaner mapping entity repository has been saved Success fully {}",savedSeatToPassangerMappingList);
            List<PassangerDetailEntity> savedPassangerDetailList= passangerDetailEntityRepository.saveAll(passangerDetailEntitiesList);
            for(PassangerDetailEntity savedPassangerDetailEntity : savedPassangerDetailList){
                System.out.println("passanger details"+savedPassangerDetailEntity.toString());
            }
            if(savedPassangerDetailList == null){
                System.out.println("unable to save the passangerEntity");
            }else{
                System.out.println("passanger data has been saved succesully "+passangerAgeList.getFirst().toString());
            }
            double ticketPrice=ticketPriceUtil.priceCalculator(trainNo,passangerAgeList,totalDistance,ticketBookingDTO.getCoachType());
            int totalNoofPassanger=passangerAgeList.size();

            TicketEntity savedTicketEntity= TicketEntity.builder()
                    .pnrNo(pnrNo)
                    .startDateOfJourney(timeandDateUtil.setTravelDate(ticketBookingDTO.getStartDateOfJourney()))
                    .endDateOfJourney(timeandDateUtil.setTravelDate(ticketBookingDTO.getStartDateOfJourney()))
                    .journeyStartStation(ticketBookingDTO.getJourneyStartStation())
                    .journeyEndStation(ticketBookingDTO.getJourneyEndStation())
                    .ticketPrice(ticketPrice)
                    .trainNo(ticketBookingDTO.getTrainNo())
                    .phoneNumber(ticketBookingDTO.getPhoneNumber())
                    .emailId(ticketBookingDTO.getEmailId())
                    .accountEmailId(userEmail)
                    .build();
            TicketEntity savedTicket=ticketEntityRepository.save(savedTicketEntity);
            updateTicketStatusEntity(ticketStatusEntity,ticketBookingDTO.getCoachType(),totalNoofPassanger);
            emailUtil.sendEmailWithTemplate(pnrNo,savedPassangerDetailList,userEmail,"User",trainNo,ticketBookingDTO.getJourneyStartStation(),ticketBookingDTO.getJourneyEndStation(),ticketBookingDTO.getStartDateOfJourney());
//            if(emailSendStatus ==false){
//                logger.error("Unable to send the email please check email util");
//            }
            if(savedTicket ==null){
                return new ServiceResponseDTO(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK.value(),"Failed to Book the ticket please try again after some time ");
            }else{
               // return new ServiceResponseDTO(Instant.now(), HttpStatus.OK.value(), HttpStatus.OK.value(),"Ticket Booked Successfully with PNR : "+savedTicket.getPnrNo()+" kindly check the email for the  ticket pdf ");
                return  ServiceResponseDTO.builder()
                       .status(HttpStatus.OK.value())
                       .code(HttpStatus.OK.value()).response(savedTicket)
                       .timestamp(Instant.now()).build();
            }

        }catch(Exception ex){
            return ServiceResponseDTO.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .code(HttpStatus.OK.value()).response("Error generated while booking the ticket "+ex.getMessage())
                    .timestamp(Instant.now()).build();
        }


    }

    @Transactional
    @Override
    public ServiceResponseDTO cancelTicket(HttpServletRequest request,String pnrNo) throws ResourceNotFoundException {
        try{
            logger.info("cancel ticket is being called from the TicketBookingService ");
            String username=tokenUtil.getUserNameFromTheToken(request.getHeader("Authorization"));
            Optional<TicketEntity> ticketEntity= Optional.ofNullable(ticketEntityRepository.findByPnrNo(pnrNo).orElseThrow(() ->
                    new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(), HttpStatus.OK.value()
                            , "their is not ticket found with the pnr no please correct it and try again ")));
            TicketEntity fetchedTicktEntity=ticketEntity.get();
            fetchedTicktEntity.setCancelled(true);
            TicketEntity updatedTicketEntity=ticketEntityRepository.save(fetchedTicktEntity);
            if(updatedTicketEntity == null){
                logger.error("failed to set the status of the ticket Entity ");
            }
            List<PassangerDetailEntity> passangerDetailEntityList=passangerDetailEntityRepository.fetchThePassangerListByPnr(pnrNo);
            int passangerNoCount=0;
            for(PassangerDetailEntity passangerDetailEntity : passangerDetailEntityList){
                passangerNoCount++;
                passangerDetailEntity.setTicketStatus(TicketStatus.CANCELLED);
            }
            List<PassangerDetailEntity> updatedPassangerDetailList=passangerDetailEntityRepository.saveAll(passangerDetailEntityList);
            if(updatedPassangerDetailList ==null){
                logger.error("failed to update the passanger detail entity ");
            }
            List<SeatToPassangerMappingEntity> mappingList=seatToPassangerMappingEntityRepository.findListByPnrNo(pnrNo);
            if(mappingList ==null){
                logger.error("failed to fetch the seat to passanger mapping entity ");
            }
            seatToPassangerMappingEntityRepository.deleteAll(mappingList);
            emailUtil.sendCancellationMail(pnrNo,"2030709@sliet.ac.in");

            return ServiceResponseDTO.builder().timestamp(Instant.now()).status(HttpStatus.OK.value()).code(HttpStatus.OK.value()).response("Ticket Cnacelled Succesfully").build();


        }catch(Exception ex){
            return ServiceResponseDTO.builder().timestamp(Instant.now()).status(HttpStatus.INTERNAL_SERVER_ERROR.value()).code(HttpStatus.OK.value()).response("CancellationFailed "+ex.getMessage()).build();


        }

    }

    @Override
    public ServiceResponseDTO getTicketDetailsByPnr(HttpServletRequest request,String pnrNo) throws ResourceNotFoundException {
        List<PassangerDetailEntity> passangerDetailEntityList=passangerDetailEntityRepository.fetchThePassangerListByPnr(pnrNo);
        if(passangerDetailEntityList.size() ==0){
            return ServiceResponseDTO.builder()
                    .response("the  passanger is not found with the pnr no")
                    .status(HttpStatus.NO_CONTENT.value())
                    .code(HttpStatus.OK.value())
                    .timestamp(Instant.now())
                    .build();
        }
        List<GetTicketByPnrNoDTO.PassangerDetails> passangerDetailsListDTO=new ArrayList<>();
        for( PassangerDetailEntity passangerDetail : passangerDetailEntityList){
            GetTicketByPnrNoDTO.PassangerDetails details =GetTicketByPnrNoDTO.PassangerDetails.builder()
                    .passangerName(passangerDetail.getPassangerName())
                    .passagerAge(passangerDetail.getAge())
                    .gender(passangerDetail.getGender())
                    .seatNo(passangerDetail.getSeatNo())
                    .coachNo(passangerDetail.getCoachNo()).build();
            passangerDetailsListDTO.add(details);
        }
        TicketEntity ticketEntity=ticketEntityRepository.findByPnrNo(pnrNo).orElseThrow(()-> new ResourceNotFoundException(HttpStatus.NO_CONTENT.value()
                ,HttpStatus.OK.value(), "the passangerDetailNot found with this pnr no please check it once again "));
        GetTicketByPnrNoDTO ticketResponse=GetTicketByPnrNoDTO.builder()
                .pnrNo(pnrNo)
                .journeyStartDate(ticketEntity.getJourneyStartStation())
                .journeyEndDate(ticketEntity.getJourneyEndStation())
                .trainName(null)
                .trainNo(ticketEntity.getTrainNo())
                .price(null)
                .ticketBookedDate(String.valueOf(ticketEntity.getCreatedAt()))
                .status((ticketEntity.isCancelled())? "Cancelled" : "LIVE")
                .ticketClass(String.valueOf(ticketEntity.getClass()))
                .passangerDetailsList(passangerDetailsListDTO)
                .build();

        return ServiceResponseDTO.builder().timestamp(Instant.now()).code(HttpStatus.OK.value()).status(HttpStatus.OK.value()).response(ticketResponse).build();
    }

    @Override
    public ServiceResponseDTO getAllBookedTicket(HttpServletRequest request) {
        List<TicketEntity> ticketEntities=ticketEntityRepository.findAll();
        if(ticketEntities.size() ==0){
            return ServiceResponseDTO.builder().timestamp(Instant.now()).status(HttpStatus.NO_CONTENT.value()).code(HttpStatus.OK.value()).response("No ticket found").build();
        }
        return ServiceResponseDTO.builder().response(ticketEntities).status(HttpStatus.OK.value()).code(HttpStatus.OK.value()).timestamp(Instant.now()).build();
    }

    @Override
    public ServiceResponseDTO cancelTicketForTheSpecific(String pnr, String passangerName, String dateOfBirth) {
        return null;
    }

    @Override
    public ServiceResponseDTO getAllCancelledTicketForAUser(HttpServletRequest request) throws ResourceNotFoundException {
        try{
            String accountEmailId=tokenUtil.getUserNameFromTheToken(request.getHeader("Authorization"));
            List<TicketEntity> cancelTicketEntityList=ticketEntityRepository.findAllCancelledTicketEntityByAccountId(accountEmailId,true);
            return  ServiceResponseDTO.builder()
                    .status(HttpStatus.OK.value())
                    .code(HttpStatus.OK.value())
                    .response(cancelTicketEntityList)
                    .timestamp(Instant.now())
                    .build();
        }catch(Exception ex){
            throw new ResourceNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK.value(), "Failed to get cancelled ticket for the user"+ex.getMessage());
        }




    }

    private List<String> alloatTheSeatNoAndCoachNo(Optional<TicketStatusEntity> ticketStatusEntity, String coachType) throws ResourceNotFoundException {
        TicketStatusEntity ticketStatus=ticketStatusEntity.get();
        List<String> resultList=new ArrayList<String>();
        if(coachType.equalsIgnoreCase("General")){
            int totalNoOfCoach=ticketStatus.getTotalGeneralCoach();
            int totalBookedSeat= ticketStatus.getTotalBookedGeneralTicket();

            int coachNo = (int) Math.ceil((double) totalBookedSeat / Constant.TOTAL_NUMBER_OF_SEAT_IN_GENERAL);
            if(coachNo > totalNoOfCoach){
                throw new ResourceNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK.value(),
                        "Not enough coach available for General class");
            }
            resultList.add("GEN"+String.valueOf(coachNo));
            if(totalBookedSeat > Constant.TOTAL_NUMBER_OF_SEAT_IN_GENERAL){
                int remainingSeat=totalBookedSeat % Constant.TOTAL_NUMBER_OF_SEAT_IN_GENERAL;
                   System.out.println("the value of the remaining seat is "+remainingSeat);
                    resultList.add(String.valueOf(remainingSeat));

            }else{
                resultList.add(String.valueOf(++totalBookedSeat));

            }



        }else if(coachType.equalsIgnoreCase("Sleeper")){
            int totalNoOfCoach=ticketStatus.getTotalSleeperCoach();
            int totalBookedSeat= ticketStatus.getTotalBookedSleeperTicket();

            int coachNo= (int) Math.ceil((double) totalBookedSeat / Constant.TOTAL_NUMBER_OF_SEAT_IN_SLEEPER);
            if(coachNo > totalNoOfCoach){
                throw new ResourceNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK.value(),
                        "Not enough coach available for General class");
            }
            resultList.add("S"+String.valueOf(coachNo));
            if(totalBookedSeat > Constant.TOTAL_NUMBER_OF_SEAT_IN_SLEEPER){
                int remainingSeat=totalBookedSeat % Constant.TOTAL_NUMBER_OF_SEAT_IN_SLEEPER;

                resultList.add(String.valueOf(remainingSeat));

            }else{
                resultList.add(String.valueOf(++totalBookedSeat));

            }

        }else if(coachType.equalsIgnoreCase("First AC")){

            int totalNoOfCoach=ticketStatus.getTotalFirstAcCoach();
            int totalBookedSeat= ticketStatus.getTotalBookedFirstACTicket();

            int coachNo= (int) Math.ceil((double) totalBookedSeat / Constant.TOTAL_NUMBER_OF_SEAT_IN_FIRST_AC);
            if(coachNo > totalNoOfCoach){
                throw new ResourceNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK.value(),
                        "Not enough coach available for General class");
            }
            resultList.add("A"+String.valueOf(coachNo));
            if(totalBookedSeat > Constant.TOTAL_NUMBER_OF_SEAT_IN_FIRST_AC){
                int remainingSeat=totalBookedSeat % Constant.TOTAL_NUMBER_OF_SEAT_IN_FIRST_AC;

                resultList.add(String.valueOf(remainingSeat));

            }else{
                resultList.add(String.valueOf(++totalBookedSeat));

            }

        }else if(coachType.equalsIgnoreCase("Second AC")){

            int totalNoOfCoach=ticketStatus.getTotalSecondACCoach();
            int totalBookedSeat= ticketStatus.getTotalBookedSecondACTicket();
            int coachNo= (int) Math.ceil((double) totalBookedSeat / Constant.TOTAL_NUMBER_OF_SEAT_IN_SECOND_AC);
            if(coachNo > totalNoOfCoach){
                throw new ResourceNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK.value(),
                        "Not enough coach available for General class");
            }
            resultList.add("B"+String.valueOf(coachNo));
            if(totalBookedSeat > Constant.TOTAL_NUMBER_OF_SEAT_IN_SECOND_AC){
                int remainingSeat=totalBookedSeat % Constant.TOTAL_NUMBER_OF_SEAT_IN_SECOND_AC;

                resultList.add(String.valueOf(remainingSeat));

            }else{
                resultList.add(String.valueOf(++totalBookedSeat));

            }
        }else if (coachType.equalsIgnoreCase("Third AC")){

            int totalNoOfCoach=ticketStatus.getTotalThirdACCoach();
            int totalBookedSeat= ticketStatus.getTotalBookedThirdACTicket();

            int coachNo= (int) Math.ceil((double) totalBookedSeat / Constant.TOTAL_NUMBER_OF_SEAT_IN_THIRD_AC);
            if(coachNo > totalNoOfCoach){
                throw new ResourceNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK.value(),
                        "Not enough coach available for General class");
            }
            resultList.add("C"+String.valueOf(coachNo));
            if(totalBookedSeat > Constant.TOTAL_NUMBER_OF_SEAT_IN_THIRD_AC){
                int remainingSeat=totalBookedSeat % Constant.TOTAL_NUMBER_OF_SEAT_IN_THIRD_AC;
                resultList.add(String.valueOf(remainingSeat));
            }else{
                resultList.add(String.valueOf(++totalBookedSeat));
            }
        }else{
            throw new ResourceNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK.value(),
                    "Invalid coach type provided");
        }
       return resultList;
    }

    public void updateTicketStatusEntity(Optional<TicketStatusEntity> ticketStatusEntity,String coachType,int totalNoOfPasanger){
        TicketStatusEntity entityToBeUpdate=ticketStatusEntity.get();
        if(coachType.equalsIgnoreCase("General")){
            entityToBeUpdate.setTotalBookedGeneralTicket(entityToBeUpdate.getTotalBookedGeneralTicket()+totalNoOfPasanger);
            entityToBeUpdate.setTotalAvailableGeneralTicket(entityToBeUpdate.getTotalAvailableGeneralTicket()-totalNoOfPasanger);

        }else if(coachType.equalsIgnoreCase("Sleeper")){
            entityToBeUpdate.setTotalBookedSleeperTicket(entityToBeUpdate.getTotalBookedSleeperTicket()+totalNoOfPasanger);
            entityToBeUpdate.setTotalAvailableSleeperTicket(entityToBeUpdate.getTotalAvailableSleeperTicket()-totalNoOfPasanger);

        }else if(coachType.equalsIgnoreCase("First AC")){
            entityToBeUpdate.setTotalBookedFirstACTicket(entityToBeUpdate.getTotalBookedFirstACTicket()+totalNoOfPasanger);
            entityToBeUpdate.setTotalAvailableFirstACTicket(entityToBeUpdate.getTotalAvailableFirstACTicket()-totalNoOfPasanger);
        }else if(coachType.equalsIgnoreCase("Second AC")){
            entityToBeUpdate.setTotalBookedSecondACTicket(entityToBeUpdate.getTotalBookedSecondACTicket()+totalNoOfPasanger);
            entityToBeUpdate.setTotalAvailableSecondACTicket(entityToBeUpdate.getTotalAvailableSecondACTicket()-totalNoOfPasanger);
        }else if(coachType.equalsIgnoreCase("Third AC")){
            entityToBeUpdate.setTotalBookedThirdACTicket(entityToBeUpdate.getTotalBookedThirdACTicket()+totalNoOfPasanger);
            entityToBeUpdate.setTotalAvailableThirdACTicket(entityToBeUpdate.getTotalAvailableThirdACTicket()-totalNoOfPasanger);
        }else{
           logger.error("updateTickeStatusEntity method of TicketBooking ServiceImpl is being excecuted and inncorrect coachtype is being provided ");
        }
         TicketStatusEntity updatedEntity=ticketStatusEntityRepository.save(entityToBeUpdate);
        if(updatedEntity == null){
            logger.error("failed to save the TicketStatusEntity ");
        }else {
            logger.info("the ticket Status Entity saved Successfully {}",updatedEntity);
        }
    }
}
