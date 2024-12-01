package com.sparx.railway.ticketapp.backend.controller;

import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import com.sparx.railway.ticketapp.backend.dto.TicketPriceRequestDTO;
import com.sparx.railway.ticketapp.backend.service.TicketService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/list")
    public ResponseEntity<ServiceResponseDTO> checkTicket(HttpServletRequest request, @RequestBody TicketPriceRequestDTO requestDTO){
        return new ResponseEntity<>(ticketService.checkTicketPrice(requestDTO), HttpStatus.OK);
    }


}
