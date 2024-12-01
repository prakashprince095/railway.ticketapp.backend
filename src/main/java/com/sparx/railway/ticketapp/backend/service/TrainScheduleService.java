package com.sparx.railway.ticketapp.backend.service;

import com.sparx.railway.ticketapp.backend.Exception.ResourceNotFoundException;
import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import com.sparx.railway.ticketapp.backend.dto.TrainScheduleDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface TrainScheduleService {
    public ServiceResponseDTO createTrainSchedule(HttpServletRequest request, TrainScheduleDTO trainScheduleDTO);
    public ServiceResponseDTO updateTrainSchedule(HttpServletRequest request, String trainScheduleId, TrainScheduleDTO trainScheduleDTO) throws ResourceNotFoundException;
    public ServiceResponseDTO deleteTrainSchedule(String trainScheduleId) throws ResourceNotFoundException;
    public ServiceResponseDTO getTrainScheduleById(String trainScheduleId) throws ResourceNotFoundException;
    public ServiceResponseDTO getAllTrainSchedules();
}
