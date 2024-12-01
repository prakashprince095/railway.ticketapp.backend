package com.sparx.railway.ticketapp.backend.service;

import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.text.ParseException;

public interface MenuService {
    public ServiceResponseDTO getMyUpcomimgBooking(HttpServletRequest request) throws ParseException;
}
