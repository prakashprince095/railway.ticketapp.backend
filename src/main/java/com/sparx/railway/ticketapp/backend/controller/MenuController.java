package com.sparx.railway.ticketapp.backend.controller;

import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import com.sparx.railway.ticketapp.backend.service.MenuService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/upcomingBooking")
    public ResponseEntity<ServiceResponseDTO> getUpcomingBooking(HttpServletRequest request) throws ParseException {
        return new ResponseEntity<>(menuService.getMyUpcomimgBooking(request), HttpStatus.OK);
    }

}
