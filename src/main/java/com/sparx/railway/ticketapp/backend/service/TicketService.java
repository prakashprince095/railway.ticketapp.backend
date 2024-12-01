package com.sparx.railway.ticketapp.backend.service;

import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import com.sparx.railway.ticketapp.backend.dto.TicketPriceRequestDTO;

public interface TicketService {
    public ServiceResponseDTO checkTicketPrice(TicketPriceRequestDTO dto);
}
