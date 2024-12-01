package com.sparx.railway.ticketapp.backend.service;

import com.sparx.railway.ticketapp.backend.Exception.ResourceNotFoundException;
import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import com.sparx.railway.ticketapp.backend.dto.TicketBookingDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface TicketBookingService {
    public ServiceResponseDTO bookTicket(HttpServletRequest request, TicketBookingDTO ticketBookingDTO);
    public ServiceResponseDTO cancelTicket(HttpServletRequest request,String pnrNo) throws ResourceNotFoundException;
    public ServiceResponseDTO getTicketDetailsByPnr(HttpServletRequest request,String pnrNo) throws ResourceNotFoundException;
    public ServiceResponseDTO getAllBookedTicket(HttpServletRequest request);
    public ServiceResponseDTO cancelTicketForTheSpecific(String pnr, String passangerName, String dateOfBirth);
    public ServiceResponseDTO getAllCancelledTicketForAUser(HttpServletRequest request) throws ResourceNotFoundException;
}
