package com.sparx.railway.ticketapp.backend.serviceimpl;

import com.sparx.railway.ticketapp.backend.Exception.ResourceNotFoundException;
import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import com.sparx.railway.ticketapp.backend.dto.TrainScheduleDTO;
import com.sparx.railway.ticketapp.backend.entities.TrainSchedule;
import com.sparx.railway.ticketapp.backend.repository.TrainScheduleEntityRepository;
import com.sparx.railway.ticketapp.backend.security.JwtService;
import com.sparx.railway.ticketapp.backend.service.TrainScheduleService;
import com.sparx.railway.ticketapp.backend.util.TimeandDateUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainScheduleServiceImpl implements TrainScheduleService {

    private final TrainScheduleEntityRepository trainScheduleEntityRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    private final TimeandDateUtil timeandDateUtil;


    @Override
    public ServiceResponseDTO createTrainSchedule(HttpServletRequest request, TrainScheduleDTO trainScheduleDTO) {
        TrainSchedule trainSchedule=modelMapper.map(trainScheduleDTO, TrainSchedule.class);
        trainSchedule.setCreatedBy(getUserNameFromTheToken(request.getHeader("Authorization")));
        List<TrainScheduleDTO.Schedules> trainScheduleListDTO=trainScheduleDTO.getSchedules();
        List<TrainSchedule.Schedules> newSchedulesList=new ArrayList<>();
        for(TrainScheduleDTO.Schedules schedules : trainScheduleListDTO){
            TrainSchedule.Schedules newSchedules=new TrainSchedule.Schedules();
            newSchedules.setStationId(schedules.getStationId());
            newSchedules.setArrivalTime(ZonedDateTime.parse(schedules.getArrivalTime()));
            newSchedules.setDepartureTime(ZonedDateTime.parse(schedules.getDepartureTime()));
            newSchedulesList.add(newSchedules);

        }
        trainSchedule.setSchedules(newSchedulesList);
        TrainSchedule savedTrainSchedule=trainScheduleEntityRepository.save(trainSchedule);
        if(savedTrainSchedule == null){
            return new ServiceResponseDTO(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK.value(),"Failed to save train schedule");
        }
        return new ServiceResponseDTO(Instant.now(), HttpStatus.OK.value(), HttpStatus.OK.value(),savedTrainSchedule );
    }

    @Override
    public ServiceResponseDTO updateTrainSchedule(HttpServletRequest request, String trainScheduleId, TrainScheduleDTO trainScheduleDTO) throws ResourceNotFoundException {
        TrainSchedule trainScheduleToBeUpdated=trainScheduleEntityRepository.findById(trainScheduleId).orElseThrow(()-> new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(),HttpStatus.OK.value(),
                "the train schedule you are looking for in order to update is not found in the database with thi id "));
        trainScheduleEntityRepository.delete(trainScheduleToBeUpdated);
        trainScheduleToBeUpdated.setTrainNo(trainScheduleDTO.getTrainNo());
        List<TrainSchedule.Schedules> trainScheduleList = new ArrayList<TrainSchedule.Schedules>();
        List<TrainScheduleDTO.Schedules> listEntered = trainScheduleDTO.getSchedules();
        for(TrainScheduleDTO.Schedules  schedules: listEntered){
            TrainSchedule.Schedules newSchedules=new TrainSchedule.Schedules();
            newSchedules.setStationId(schedules.getStationId());
            newSchedules.setArrivalTime(ZonedDateTime.parse(schedules.getArrivalTime()));
            newSchedules.setDepartureTime(ZonedDateTime.parse(schedules.getDepartureTime()));
            trainScheduleList.add(newSchedules);
        }
        trainScheduleToBeUpdated.setSchedules(trainScheduleList);
        trainScheduleToBeUpdated.setUpdatedBy(getUserNameFromTheToken(request.getHeader("Authorization")));
       TrainSchedule updatedSchedule= trainScheduleEntityRepository.save(trainScheduleToBeUpdated);
       if(updatedSchedule != null){
           return  ServiceResponseDTO.builder().timestamp(Instant.now()).response(updatedSchedule).status(HttpStatus.OK.value()).code(HttpStatus.OK.value()).build();
       }else{
           return new ServiceResponseDTO(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK.value(),"Failed to update train schedule");
       }
    }
    @Override
    public ServiceResponseDTO deleteTrainSchedule(String trainScheduleId) throws ResourceNotFoundException {
        TrainSchedule scheduleToBeDeleted=trainScheduleEntityRepository.findById(trainScheduleId).orElseThrow(()
                ->new ResourceNotFoundException("could not found the schedule associated with this id "));
        try{
            trainScheduleEntityRepository.delete(scheduleToBeDeleted);
            return  ServiceResponseDTO.builder().timestamp(Instant.now()).status(HttpStatus.OK.value()).code(HttpStatus.OK.value()).response("the train schedule is deleted successfully").build();
        }catch (Exception ex){
            return ServiceResponseDTO.builder().timestamp(Instant.now()).status(HttpStatus.INTERNAL_SERVER_ERROR.value()).code(HttpStatus.OK.value()).response("failed to delete the schedule "+ex.getMessage()).build();
        }
    }

    @Override
    public ServiceResponseDTO getTrainScheduleById(String trainScheduleId) throws ResourceNotFoundException {
        TrainSchedule trainSchedule=trainScheduleEntityRepository.findById(trainScheduleId).orElseThrow(()->
                new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(),HttpStatus.OK.value(), "data not found with the corresponding id "));
        return new ServiceResponseDTO(Instant.now(), HttpStatus.OK.value(), HttpStatus.OK.value(),trainSchedule);
    }

    @Override
    public ServiceResponseDTO getAllTrainSchedules() {
        List<TrainSchedule> trainSchedules = trainScheduleEntityRepository.findAll();
        if(trainSchedules == null) {
            return new ServiceResponseDTO(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    HttpStatus.OK.value(),"Failed to fetch all train schedules or the table may be empty ");
        }else{
            return new ServiceResponseDTO(Instant.now(), HttpStatus.OK.value(), HttpStatus.OK.value(),trainSchedules);
        }

    }
    private String getUserNameFromTheToken(String token){

        return jwtService.extractUsername(token.substring(7));
    }
}
