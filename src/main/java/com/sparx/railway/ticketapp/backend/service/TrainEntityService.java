package com.sparx.railway.ticketapp.backend.service;

import com.sparx.railway.ticketapp.backend.Exception.ResourceNotFoundException;
import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import com.sparx.railway.ticketapp.backend.dto.TrainEntityDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface TrainEntityService {
    public ServiceResponseDTO cretaeTrainEntity(HttpServletRequest request, TrainEntityDTO trainEntityDTO);
    public ServiceResponseDTO updateTrainEntity(HttpServletRequest request,String trainId,TrainEntityDTO trainEntityDTO) throws ResourceNotFoundException;
    public ServiceResponseDTO deleteTrainEntity(String trainId) throws ResourceNotFoundException;
    public ServiceResponseDTO getTrainEntityById(String trainId) throws ResourceNotFoundException;
    public ServiceResponseDTO getAllTrainEntities();

}
