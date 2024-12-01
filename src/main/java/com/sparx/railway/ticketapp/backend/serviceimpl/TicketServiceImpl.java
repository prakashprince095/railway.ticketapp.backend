package com.sparx.railway.ticketapp.backend.serviceimpl;

import com.sparx.railway.ticketapp.backend.constant.DistanceConstant;
import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import com.sparx.railway.ticketapp.backend.dto.TicketPriceDTO;
import com.sparx.railway.ticketapp.backend.dto.TicketPriceRequestDTO;
import com.sparx.railway.ticketapp.backend.entities.CoachEntity;
import com.sparx.railway.ticketapp.backend.entities.StationEntity;
import com.sparx.railway.ticketapp.backend.entities.TrainEntity;
import com.sparx.railway.ticketapp.backend.repository.CoachEntityRepository;
import com.sparx.railway.ticketapp.backend.repository.StationEntityRepository;
import com.sparx.railway.ticketapp.backend.repository.TrainEntityRepository;
import com.sparx.railway.ticketapp.backend.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final StationEntityRepository stationRepository;
    private final CoachEntityRepository coachEntityRepository;
    private final TrainEntityRepository trainEntityRepository;

    Logger logger=LoggerFactory.getLogger(TicketServiceImpl.class);


    @Override
    public ServiceResponseDTO checkTicketPrice(TicketPriceRequestDTO dto) {
        try{
            Optional<StationEntity> startStationEntity= stationRepository.findByStationName(dto.getStartStation());
            logger.info("tickerServiceImpl checkTicketPrice method is being executed {}",startStationEntity);
            Optional<StationEntity> endStationEntity= stationRepository.findByStationName(dto.getEndStation());

            int totalDistance=Math.abs(startStationEntity.get().getDistance()-endStationEntity.get().getDistance());
            double facilityFactor=0;
            if(!startStationEntity.isPresent() && !endStationEntity.isPresent()){
                System.out.println("The searched Station doesnot exist ");
            }
            TicketPriceDTO ticketPriceResponse=TicketPriceDTO.builder().startStation(startStationEntity.get().getStationName())
                    .endStation(endStationEntity.get().getStationName()).totalDistance(totalDistance).build();
            List<TicketPriceDTO.TicketPrice >  ticketPriceList=new ArrayList<TicketPriceDTO.TicketPrice>();
            List<Integer> startStationTrainNoList=startStationEntity.get().getTrainNoList();
            List<Integer> endStationTrainNoList=endStationEntity.get().getTrainNoList();
            List<Integer> resultantTrainNoList=new ArrayList<Integer>();
            for(Integer trainNo : startStationTrainNoList){
                if(endStationTrainNoList.contains(trainNo)){

                            logger.info("the result of the find by train no {}",trainEntityRepository.findByTrainNo(trainNo).get().getTrainName());
                    int resultantNo= startStationEntity.get().getSerialNo()-endStationEntity.get().getSerialNo();
                    if(resultantNo < 0){
                        System.out.println("the resultant is less than zero it means that the all the train result should be a Up route train");
                        TrainEntity trainEntity =trainEntityRepository.findByTrainNo(trainNo).get();
                        facilityFactor=trainEntity.getFacilityFactor();
                        System.out.println("the train is "+trainEntity.getTrainName()+"the value of the facility factor is " + facilityFactor);
                        if(trainEntity.getRouteType().equalsIgnoreCase("up")){
                            resultantTrainNoList.add(trainNo);
                        }
                        System.out.println("the list of Up is "+resultantTrainNoList);

                    }else{
                        System.out.println("the resultant is greater than zero it means that the all the train result should be a down route train " );
                        TrainEntity trainEntity =trainEntityRepository.findByTrainNo(trainNo).get();
                        facilityFactor=trainEntity.getFacilityFactor();
                        System.out.println("the train is "+trainEntity.getTrainName()+"the value of the facility factor is " + facilityFactor);                        if(trainEntity.getRouteType().equalsIgnoreCase("down")){
                            resultantTrainNoList.add(trainNo);
                        }
                        System.out.println("the list of down is "+resultantTrainNoList);
                    }
                }
            }
              System.out.println("the final resultant list is "+resultantTrainNoList);
//            System.out.println("this is the list of train no that stats at the same station "+startStationTrainNoList.toString());
//            System.out.println("this is the list of train no that ends at the same station "+endStationTrainNoList.toString());
//             for(int trainNo : startStationTrainNoList){
//                 logger.info("control is inside the for loop "+trainNo);
//                 if(endStationTrainNoList.contains(trainNo)){
                   for(int trainNo : resultantTrainNoList){

                     CoachEntity coachEntity =coachEntityRepository.findByTrainNo(trainNo).get();
                       facilityFactor=trainEntityRepository.findByTrainNo(trainNo).get().getFacilityFactor();
                     List<CoachEntity.CoachDetails> firstACCoachDetails=coachEntity.getFirstACCoachDetails();
                     setThePriceForEachTrain(firstACCoachDetails,ticketPriceList,trainNo,totalDistance,facilityFactor);
                     List<CoachEntity.CoachDetails> secondACCoachDetails= coachEntity.getSecondACCoachDetails();
                     setThePriceForEachTrain(secondACCoachDetails,ticketPriceList,trainNo,totalDistance,facilityFactor);
                     List<CoachEntity.CoachDetails> thirdACCoachDetails=coachEntity.getThirdACCoachDetails();
                     setThePriceForEachTrain(thirdACCoachDetails,ticketPriceList,trainNo,totalDistance,facilityFactor);
                     List<CoachEntity.CoachDetails> sleeperCoachDetails=coachEntity.getSleeperCoachDetails();
                     setThePriceForEachTrain(sleeperCoachDetails,ticketPriceList,trainNo,totalDistance,facilityFactor);
                     List<CoachEntity.CoachDetails> generalCoachDetails=coachEntity.getGeneralCoachDetails();
                     setThePriceForEachTrain(generalCoachDetails,ticketPriceList,trainNo,totalDistance,facilityFactor);
                     logger.info("the ticket price  setting is succcesfull");
//                     ticketPriceList.add(TicketPriceDTO.TicketPrice.builder().trainNo(trainNo).classType("AC").price(100).noOfTicketAvailable("100").build());

             }
//            ticketPriceList.add(TicketPriceDTO.TicketPrice.builder().trainNo(1).classType("AC").price(100).noOfTicketAvailable("100").build());
            ticketPriceResponse.setTicketPriceList(ticketPriceList);
             logger.info("check ticket price method executed succesfully and ready to return the response ");
            return ServiceResponseDTO.builder().timestamp(Instant.now()).status(HttpStatus.OK.value()).code(HttpStatus.OK.value()).response(ticketPriceResponse).build();

        }catch (Exception ex){
            logger.error("error in check ticket price method "+ex.getMessage());
            return new ServiceResponseDTO(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK.value(),"Failed to check ticket price"+ex.getMessage());
        }

    }
    private void setThePriceForEachTrain(List<CoachEntity.CoachDetails> coachDetails,List<TicketPriceDTO.TicketPrice >  ticketPriceList,int trainNo,int distance,double facilityFactor){
        logger.info("setThePriceForEachTrain method of the ticket Service impl class is being executing and the response ");
        logger.info("the facility factor in the setPriceForEachTrain method is: " + facilityFactor);
        CoachEntity.CoachDetails detail=coachDetails.getFirst();
        int totalSeat=0;
        double ticketPrice=0;
        String coachType="";
        if(detail.getCoachType().equalsIgnoreCase("General")){
           logger.info("the price is getting set for the General category");
            for(CoachEntity.CoachDetails res: coachDetails){
                totalSeat +=res.getTotalNoOfSeat();
                ticketPrice = distance* DistanceConstant.RATE_FOR_GENRAL_PER_KM * facilityFactor;
                coachType="General";
            }
            
        }else if(detail.getCoachType().equalsIgnoreCase("Sleeper")){
            logger.info("the price is getting set for the Sleeper category");

            for(CoachEntity.CoachDetails res: coachDetails){
                totalSeat +=res.getTotalNoOfSeat();
                ticketPrice = distance* DistanceConstant.RATE_FOR_SLEEPERCLASS_PER_KM * facilityFactor;
                coachType="Sleeper";
            }
        } else if (detail.getCoachType().equalsIgnoreCase("First AC")) {
            logger.info("the price is getting set for the First category");

            for(CoachEntity.CoachDetails res: coachDetails){
                totalSeat +=res.getTotalNoOfSeat();
                ticketPrice = distance* DistanceConstant.RATE_FOR_FIRST_CLASS_AC_PER_KM * facilityFactor;
                coachType="First AC";
            }
        }else if(detail.getCoachType().equalsIgnoreCase("Second AC")){
            logger.info("the price is getting set for the Second Ac category");

            for(CoachEntity.CoachDetails res: coachDetails){
                totalSeat +=res.getTotalNoOfSeat();
                ticketPrice = distance* DistanceConstant.RATE_FOR_SECOND_CLASS_AC_PER_KM * facilityFactor;
                coachType="Second AC";
            }
        }else if(detail.getCoachType().equalsIgnoreCase("Third AC")){
            logger.info("the price is getting set for the Third  category");

            for(CoachEntity.CoachDetails res: coachDetails){
                totalSeat +=res.getTotalNoOfSeat();
                ticketPrice = distance* DistanceConstant.RATE_FOR_THIRD_CLASS_SL_PER_KM * facilityFactor;
                coachType="Third AC";
            }
        }else{

        }

        ticketPriceList.add(TicketPriceDTO.TicketPrice.builder().trainNo(trainNo).classType(coachType).price(ticketPrice).noOfTicketAvailable(String.valueOf(totalSeat)).build());

    }

}
