package com.sparx.railway.ticketapp.backend.serviceimpl;


import com.sparx.railway.ticketapp.backend.Exception.ResourceNotFoundException;
import com.sparx.railway.ticketapp.backend.constant.Constant;
import com.sparx.railway.ticketapp.backend.dto.CoachEntityDTO;
import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import com.sparx.railway.ticketapp.backend.dto.TicketStatusEntityDTO;
import com.sparx.railway.ticketapp.backend.entities.CoachEntity;
import com.sparx.railway.ticketapp.backend.entities.TicketStatusEntity;
import com.sparx.railway.ticketapp.backend.repository.CoachEntityRepository;
import com.sparx.railway.ticketapp.backend.repository.TicketStatusEntityRepository;
import com.sparx.railway.ticketapp.backend.service.CoachService;
import com.sparx.railway.ticketapp.backend.util.TimeandDateUtil;
import com.sparx.railway.ticketapp.backend.util.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService {

    private final CoachEntityRepository coachEntityRepository;
    private final ModelMapper modelMapper;
    private final TokenUtil tokenUtil;
    private final TicketStatusEntityRepository ticketStatusEntityRepository;
    private final TimeandDateUtil timeAndDateUtil;
    @Override
    public ServiceResponseDTO addCoach(HttpServletRequest request,CoachEntityDTO coachEntityDTO) {
       CoachEntity coachEntity= modelMapper.map(coachEntityDTO, CoachEntity.class);
       coachEntity.setCreatedBy(tokenUtil.getUserNameFromTheToken(request.getHeader("Authorization")));
       CoachEntity savedCoachEntity=coachEntityRepository.save(coachEntity);
       if(savedCoachEntity == null){
           return new ServiceResponseDTO(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK.value(),"Failed to save coach entity");
       }else{
           return new ServiceResponseDTO(Instant.now(), HttpStatus.OK.value(), HttpStatus.OK.value(),savedCoachEntity );
       }

    }

    @Override
    public ServiceResponseDTO updateCoach(HttpServletRequest request,String coachId, CoachEntityDTO coachEntityDTO) {
        CoachEntity entityTobeUpdated=coachEntityRepository.findById(coachId).get();
        coachEntityRepository.delete(entityTobeUpdated);
        entityTobeUpdated.setUpdatedBy(tokenUtil.getUserNameFromTheToken(request.getHeader("Authorization")));
        CoachEntity newCoachEntity=modelMapper.map(coachEntityDTO,CoachEntity.class);
        CoachEntity savedCoachEntity=coachEntityRepository.save(newCoachEntity);
        if(savedCoachEntity == null){
            return new ServiceResponseDTO(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK.value(),"Failed to update coach entity");
        }else{
            return new ServiceResponseDTO(Instant.now(), HttpStatus.OK.value(), HttpStatus.OK.value(),savedCoachEntity);
        }
    }

    @Override
    public ServiceResponseDTO deleteCoach(HttpServletRequest request,String coachId) throws ResourceNotFoundException {
        try{

            CoachEntity entityTodeleted=coachEntityRepository.findById(coachId).orElseThrow(()->new
                    ResourceNotFoundException(HttpStatus.NO_CONTENT.value(), HttpStatus.OK.value(), "there is no data present in the database for this id"));
            coachEntityRepository.delete(entityTodeleted);
            return new ServiceResponseDTO(Instant.now(), HttpStatus.OK.value(), HttpStatus.OK.value(),"Coach entity deleted successfully");
        }catch(Exception ex){
            return  ServiceResponseDTO.builder().timestamp(Instant.now())
                    .response("unable to delete something went wrong")
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value()).code(HttpStatus.OK.value()).build();
        }
    }

    @Override
    public ServiceResponseDTO getCoachById(HttpServletRequest request,String coachId) throws ResourceNotFoundException {
        CoachEntity fetchedCoachEntity=coachEntityRepository.findById(coachId).orElseThrow(()
                -> new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(),HttpStatus.OK.value(),"the reesource is not found with the request id"));
        CoachEntityDTO response=modelMapper.map(fetchedCoachEntity,CoachEntityDTO.class);
        return new ServiceResponseDTO(Instant.now(), HttpStatus.OK.value(), HttpStatus.OK.value(), response);
    }

    @Override
    public ServiceResponseDTO getCoachByTrainNo(HttpServletRequest request, int trainNo) throws ResourceNotFoundException {
       Optional<CoachEntity> coachEntity= Optional.ofNullable(coachEntityRepository.findByTrainNo(trainNo)
               .orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NO_CONTENT.value(), HttpStatus.OK.value(),
                       "there is no resouce in the database for this train no")));
       return ServiceResponseDTO.builder().timestamp(Instant.now())
               .response(modelMapper.map(coachEntity,CoachEntityDTO.class))
               .status(HttpStatus.OK.value())
               .code(HttpStatus.OK.value())
               .build();

    }

    @Override
    public ServiceResponseDTO getAllCoaches(HttpServletRequest request,int pageNumber, int recordPerPage) {
        Pageable pageable = PageRequest.of(pageNumber, recordPerPage, Sort.by(Sort.Order.asc("trainNo")));
        Page<CoachEntity> coachEntityList =  coachEntityRepository.findAll(pageable);
        List<CoachEntityDTO> responseList=coachEntityList.stream().map(coachEntity -> modelMapper.map(coachEntity,CoachEntityDTO.class)).collect(Collectors.toList());
        if(responseList.size() ==0){
            return new ServiceResponseDTO(Instant.now(), HttpStatus.OK.value(), HttpStatus.OK.value(),"No coach found");
        }else{
            return new ServiceResponseDTO(Instant.now(), HttpStatus.OK.value(), HttpStatus.OK.value(), responseList);
        }
    }

    @Override
    public ServiceResponseDTO assignCoachToTrain(HttpServletRequest request, TicketStatusEntityDTO ticketStatusEntityDTO) throws ParseException {
        try{
            TicketStatusEntity ticketStatusEntity=TicketStatusEntity.builder()
                    .trainNo(ticketStatusEntityDTO.getTrainNo())
                    .totalGeneralCoach(ticketStatusEntityDTO.getTotalGeneralCoach())
                    .totalAvailableGeneralTicket(Constant.TOTAL_NUMBER_OF_SEAT_IN_GENERAL*ticketStatusEntityDTO.getTotalGeneralCoach())
                    .totalBookedGeneralTicket(0)
                    .generalBookedSeatNoList(null)
                    .totalSleeperCoach(ticketStatusEntityDTO.getTotalSleeperCoach())
                    .totalAvailableSleeperTicket(Constant.TOTAL_NUMBER_OF_SEAT_IN_SLEEPER * ticketStatusEntityDTO.getTotalSleeperCoach())
                    .totalBookedSleeperTicket(0)
                    .sleeperBookedSeatNoList(null)
                    .totalFirstAcCoach(ticketStatusEntityDTO.getTotalFirstACCoach())
                    .totalAvailableFirstACTicket(Constant.TOTAL_NUMBER_OF_SEAT_IN_FIRST_AC * ticketStatusEntityDTO.getTotalFirstACCoach())
                    .totalBookedFirstACTicket(0)
                    .firstACBookedSeatNoList(null)
                    .totalSecondACCoach(ticketStatusEntityDTO.getTotalSecondACCoach())
                    .totalAvailableSecondACTicket(Constant.TOTAL_NUMBER_OF_SEAT_IN_SECOND_AC * ticketStatusEntityDTO.getTotalSecondACCoach())
                    .totalBookedSecondACTicket(0)
                    .secondACBookedSeatNoList(null)
                    .totalThirdACCoach(ticketStatusEntityDTO.getTotalThirdACCoach())
                    .totalAvailableThirdACTicket(Constant.TOTAL_NUMBER_OF_SEAT_IN_THIRD_AC * ticketStatusEntityDTO.getTotalThirdACCoach())
                    .totalBookedThirdACTicket(0)
                    .thirdACBookedSeatNoList(null)
                    .startDateofTheJourney(timeAndDateUtil.setTravelDate(ticketStatusEntityDTO.getStartDateofJourney()))
                    .endDateofTheJourney(timeAndDateUtil.setTravelDate(ticketStatusEntityDTO.getEndDateOfJourney()))
                    .createdBy(tokenUtil.getUserNameFromTheToken(request.getHeader("Authorization")))
                    .build();


            TicketStatusEntity savedTicketStatusEntity=ticketStatusEntityRepository.save(ticketStatusEntity);
            if(savedTicketStatusEntity == null){
                return new ServiceResponseDTO(Instant.now(),HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK.value(),"Unable to save ticket status entity");
            }else{
                return new ServiceResponseDTO(Instant.now(), HttpStatus.OK.value(), HttpStatus.OK.value(), savedTicketStatusEntity);
            }

        }catch (Exception ex){
            return new ServiceResponseDTO(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK.value(),"Failed to assign coach to train"+ex.getMessage());
        }

    }

}
