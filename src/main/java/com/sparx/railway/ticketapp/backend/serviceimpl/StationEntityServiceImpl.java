package com.sparx.railway.ticketapp.backend.serviceimpl;

import com.sparx.railway.ticketapp.backend.Exception.ResourceNotFoundException;
import com.sparx.railway.ticketapp.backend.dto.AddTrainListToStationDTO;
import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import com.sparx.railway.ticketapp.backend.dto.StationEntityDTO;
import com.sparx.railway.ticketapp.backend.entities.StationEntity;
import com.sparx.railway.ticketapp.backend.repository.StationEntityRepository;
import com.sparx.railway.ticketapp.backend.security.JwtService;
import com.sparx.railway.ticketapp.backend.service.StationEntityService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StationEntityServiceImpl implements StationEntityService {
    private final StationEntityRepository stationEntityRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    @Override
    public ServiceResponseDTO createSation(HttpServletRequest request, StationEntityDTO stationEntityDTO) {
        StationEntity stationEntity=modelMapper.map(stationEntityDTO, StationEntity.class);
        stationEntity.setCreatedBy(getUserNameFromToken(request.getHeader("Authorization")));
        StationEntity savedStationEntity=stationEntityRepository.save(stationEntity);
        if(savedStationEntity == null){
            return new ServiceResponseDTO(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK.value(),"Failed to save station entity");
        }
        return new ServiceResponseDTO(Instant.now(), HttpStatus.OK.value(), HttpStatus.OK.value(),savedStationEntity );

    }

    @Override
    public ServiceResponseDTO updateStation(HttpServletRequest request,String stationId, StationEntityDTO stationEntityDTO) throws ResourceNotFoundException {
        StationEntity stationEntityToBeUpdated=stationEntityRepository.findById(stationId).orElseThrow(()-> new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(),HttpStatus.OK.value(), "station not found with this station id"));
        stationEntityToBeUpdated.setStationCode(stationEntityDTO.getStationCode());
        stationEntityToBeUpdated.setStationName(stationEntityDTO.getStationName());
        stationEntityToBeUpdated.setDistance(Integer.parseInt(stationEntityDTO.getDistance()));
        stationEntityToBeUpdated.setStateName(stationEntityDTO.getStateName());
        stationEntityToBeUpdated.setUpdatedBy(getUserNameFromToken(request.getHeader("Authorization")));

        StationEntity savedStationEntity=stationEntityRepository.save(stationEntityToBeUpdated);
        if(savedStationEntity == null){
            return new ServiceResponseDTO(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK.value(),"Failed to update station entity");
        }
        return new ServiceResponseDTO(Instant.now(), HttpStatus.OK.value(), HttpStatus.OK.value(),modelMapper.map(savedStationEntity,StationEntityDTO.class));
    }

    @Override
    public ServiceResponseDTO deleteStation(String stationId) throws ResourceNotFoundException {
       StationEntity stationEntityToBeDeleted= stationEntityRepository.findById(stationId).orElseThrow(()-> new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(),HttpStatus.OK.value(), "unable to delete becacuse no entity found with the id " + stationId));
       try{
           stationEntityRepository.delete(stationEntityToBeDeleted);
           return  ServiceResponseDTO.builder().timestamp(Instant.now()).status(HttpStatus.OK.value()).code(HttpStatus.OK.value()).response("the station entity is being deleted sucessfully") .build();
       }catch (Exception ex){
           return new ServiceResponseDTO(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK.value(),"Failed to delete station entity: " + ex.getMessage());

       }
    }

    @Override
    public ServiceResponseDTO getStation(String stationId) throws ResourceNotFoundException {
        StationEntity stationEntity= stationEntityRepository.findById(stationId).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(),HttpStatus.OK.value(), "Failed to find station entity"));
        return new ServiceResponseDTO(Instant.now(), HttpStatus.OK.value(), HttpStatus.OK.value(),modelMapper.map(stationEntity, StationEntityDTO.class));
    }

    @Override
    public ServiceResponseDTO getAllStation(int pageNumber,int recordsPerPage) {
//        int page = 0;
//        int size = 20;
        String sortField = "distance"; // Example field, could be dynamic
        String sortDirection = "asc"; // Example direction, could be dynamic

        Sort sort = Sort.by(Sort.Order.by(sortField).with(Sort.Direction.fromString(sortDirection)));
        Pageable pageable = PageRequest.of(pageNumber, recordsPerPage, sort);

//        List<StationEntity> stationEntityList= (List<StationEntity>) stationEntityRepository.findAll(pageable);
       Page<StationEntity> fetchedStationList= stationEntityRepository.findAll(pageable);
        if(fetchedStationList.isEmpty()){
            return ServiceResponseDTO.builder().timestamp(Instant.now()).status(HttpStatus.NO_CONTENT.value()).code(HttpStatus.OK.value()).response("the station list is empty").build();
        }
        List<StationEntityDTO> stationEntityDTOList=fetchedStationList.stream().map(station->modelMapper.map(station, StationEntityDTO.class)).collect(Collectors.toList());
        return  ServiceResponseDTO.builder().timestamp(Instant.now()).status(HttpStatus.OK.value()).code(HttpStatus.OK.value()).response(stationEntityDTOList).build();
    }

    @Override
    public ServiceResponseDTO addTrainListToStationEntity(HttpServletRequest request, String stationId, AddTrainListToStationDTO trainList) throws ResourceNotFoundException {
        StationEntity stationEntity=stationEntityRepository.findById(stationId).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(), HttpStatus.OK.value(), "station not found with this station id"));
         stationEntity.setTrainNoList(trainList.getTrainList());
         StationEntity updatedStationEntity=stationEntityRepository.save(stationEntity);
         if(updatedStationEntity == null){
             return new ServiceResponseDTO(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK.value(),"Failed to update station entity with train list");
         }else{
             return new ServiceResponseDTO(Instant.now(), HttpStatus.OK.value(), HttpStatus.OK.value(),updatedStationEntity.getTrainNoList());
         }

    }

    private String getUserNameFromToken(String token){
        return jwtService.extractUsername(token.substring(7));
    }
}
