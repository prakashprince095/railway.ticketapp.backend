package com.sparx.railway.ticketapp.backend.serviceimpl;

import com.sparx.railway.ticketapp.backend.Exception.ResourceNotFoundException;
import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import com.sparx.railway.ticketapp.backend.dto.TrainEntityDTO;
import com.sparx.railway.ticketapp.backend.entities.TrainEntity;
import com.sparx.railway.ticketapp.backend.repository.TrainEntityRepository;
import com.sparx.railway.ticketapp.backend.security.JwtService;
import com.sparx.railway.ticketapp.backend.service.TrainEntityService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainEntityServiceImpl implements TrainEntityService {
    private final ModelMapper modelMapper;
    private final TrainEntityRepository trainEntityRepository;
    private final JwtService jwtService;
    @Override
    public ServiceResponseDTO cretaeTrainEntity(HttpServletRequest request, TrainEntityDTO trainEntityDTO) {
        TrainEntity trainEntity=modelMapper.map(trainEntityDTO, TrainEntity.class);
        trainEntity.setCreatedBy(getUserNameFromTheToken(request.getHeader("Authorization")));
        TrainEntity savedTrainEntity=trainEntityRepository.save(trainEntity);
        if(savedTrainEntity == null){
            return new ServiceResponseDTO(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.OK.value(),"Failed to save train entity");
        }
        return new ServiceResponseDTO(Instant.now(), HttpStatus.OK.value(),HttpStatus.OK.value(),savedTrainEntity );
    }

    @Override
    public ServiceResponseDTO updateTrainEntity(HttpServletRequest request,String trainId,TrainEntityDTO trainEntityDTO) throws ResourceNotFoundException {
       TrainEntity trainEntityToBeUpdated=trainEntityRepository.findById(trainId).
               orElseThrow(()-> new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(),HttpStatus.OK.value()
                       , "the train entity not found with this requested id"));
       trainEntityToBeUpdated.setTrainName(trainEntityDTO.getTrainName());
       trainEntityToBeUpdated.setTrainNo(trainEntityDTO.getTrainNo());
       trainEntityToBeUpdated.setStartStation(trainEntityDTO.getStartStation());
       trainEntityToBeUpdated.setEndStation(trainEntityDTO.getEndStation());
       trainEntityToBeUpdated.setStartTime(trainEntityDTO.getStartTime());
       trainEntityToBeUpdated.setEndTime(trainEntityDTO.getEndTime());
       trainEntityToBeUpdated.setUpdatedBy(getUserNameFromTheToken(request.getHeader("Authorization")));

       TrainEntity updatedTrainEntity=trainEntityRepository.save(trainEntityToBeUpdated);
      if( updatedTrainEntity == null){
          return new ServiceResponseDTO(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.OK.value(),"Failed to update train entity");
      }else{
          return new ServiceResponseDTO(Instant.now(), HttpStatus.OK.value(),HttpStatus.OK.value(),updatedTrainEntity);
      }



    }

    @Override
    public ServiceResponseDTO deleteTrainEntity(String trainId) throws ResourceNotFoundException {
        Optional<TrainEntity> trainEntityToBeDeleted= Optional.ofNullable(trainEntityRepository.findById(trainId).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(), HttpStatus.OK.value(), "train could not be found with this id")));
        try{
            trainEntityRepository.delete(trainEntityToBeDeleted.get());
            return new ServiceResponseDTO(Instant.now(), HttpStatus.OK.value(), HttpStatus.OK.value(),"Train entity deleted successfully");
        }catch (Exception ex){
            return new ServiceResponseDTO(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK.value(), "something went wrong "+ex.getMessage());
        }
//        trainEntityRepository.delete(trainEntityToBeDeleted.get());
//        return null;
    }

    @Override
    public ServiceResponseDTO getTrainEntityById(String trainId) throws ResourceNotFoundException {
        TrainEntity fetchedTrainEntity=trainEntityRepository.findById(trainId).orElseThrow(()->new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(),HttpStatus.OK.value(), "train could be found with this given id "));

        return new ServiceResponseDTO(Instant.now(),HttpStatus.OK.value(), HttpStatus.OK.value(), fetchedTrainEntity);
    }

    @Override
    public ServiceResponseDTO getAllTrainEntities() {
        List<TrainEntity> trainEntityList=trainEntityRepository.findAll();
        if(trainEntityList.size() == 0){
            return ServiceResponseDTO.builder().timestamp(Instant.now()).status(HttpStatus.NO_CONTENT.value()).code(HttpStatus.OK.value()).response("the train list is empty").build();
        }
        List<TrainEntityDTO> trainEntityDTOList=trainEntityList.stream().map(train-> modelMapper.map(train,TrainEntityDTO.class)).collect(Collectors.toList());

        return ServiceResponseDTO.builder().timestamp(Instant.now()).response(trainEntityDTOList).status(HttpStatus.OK.value()).code(HttpStatus.NO_CONTENT.value()).build();
    }

    private String getUserNameFromTheToken(String token){

        return jwtService.extractUsername(token.substring(7));
    }
}
