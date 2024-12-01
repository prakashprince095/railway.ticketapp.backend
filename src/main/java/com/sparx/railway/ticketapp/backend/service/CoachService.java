package com.sparx.railway.ticketapp.backend.service;

import com.sparx.railway.ticketapp.backend.Exception.ResourceNotFoundException;
import com.sparx.railway.ticketapp.backend.dto.CoachEntityDTO;
import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import com.sparx.railway.ticketapp.backend.dto.TicketStatusEntityDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.security.Provider;
import java.text.ParseException;

public interface CoachService {
    public ServiceResponseDTO addCoach(HttpServletRequest request, CoachEntityDTO coachEntityDTO);
    public ServiceResponseDTO updateCoach(HttpServletRequest request,String coachId, CoachEntityDTO coachEntityDTO);
    public ServiceResponseDTO deleteCoach(HttpServletRequest request,String coachId) throws ResourceNotFoundException;
    public ServiceResponseDTO getCoachById(HttpServletRequest request,String coachId) throws ResourceNotFoundException;
    public ServiceResponseDTO getCoachByTrainNo(HttpServletRequest request,int trainNo) throws ResourceNotFoundException;
    public ServiceResponseDTO getAllCoaches(HttpServletRequest request,int pageNumber, int recordPerPage);
    public ServiceResponseDTO assignCoachToTrain(HttpServletRequest request, TicketStatusEntityDTO ticketStatusEntityDTO) throws ParseException;
}
