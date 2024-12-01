package com.sparx.railway.ticketapp.backend.controller;

import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import com.sparx.railway.ticketapp.backend.entities.TicketStatusEntity;
import com.sparx.railway.ticketapp.backend.repository.TicketStatusEntityRepository;
import com.sparx.railway.ticketapp.backend.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final JwtService jwtService;
    private final TicketStatusEntityRepository ticketStatusEntityRepository;
    @GetMapping("/test")
    public ResponseEntity<String> testMethod(HttpServletRequest request){
        String token =request.getHeader("Authorization");
        String username= jwtService.extractUsername(token.substring(7));
        return  new ResponseEntity<String>("getting the response from the test method from the test controller "+username, HttpStatus.OK);
    }
//    @GetMapping("/create")
//    public ResponseEntity<ServiceResponseDTO> createTicketStatusEntity(){
//        TicketStatusEntity.builder().trainNo(77).totalAvailableGeneralTicket(8).totalBookedGeneralTicket(33).
//        return null;
//    }

}

