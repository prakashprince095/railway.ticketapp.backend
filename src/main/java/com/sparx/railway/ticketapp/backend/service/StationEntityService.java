package com.sparx.railway.ticketapp.backend.service;

import com.sparx.railway.ticketapp.backend.Exception.ResourceNotFoundException;
import com.sparx.railway.ticketapp.backend.dto.AddTrainListToStationDTO;
import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import com.sparx.railway.ticketapp.backend.dto.StationEntityDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface StationEntityService {
    public ServiceResponseDTO createSation(HttpServletRequest request,StationEntityDTO stationEntityDTO);
    public ServiceResponseDTO updateStation(HttpServletRequest request,String stationId,StationEntityDTO stationEntityDTO) throws ResourceNotFoundException;
    public ServiceResponseDTO deleteStation(String stationId) throws ResourceNotFoundException;
    public ServiceResponseDTO getStation(String stationId) throws ResourceNotFoundException;
    public ServiceResponseDTO getAllStation(int pageNumber,int recordsPerPage);
    public ServiceResponseDTO addTrainListToStationEntity(HttpServletRequest request, String stationId, AddTrainListToStationDTO trainList) throws ResourceNotFoundException;
}
