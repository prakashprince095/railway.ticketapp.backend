package com.sparx.railway.ticketapp.backend.controller;

import com.sparx.railway.ticketapp.backend.Exception.ResourceNotFoundException;
import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import com.sparx.railway.ticketapp.backend.dto.TicketBookingDTO;
import com.sparx.railway.ticketapp.backend.service.TicketBookingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/ticket/booking")
@RequiredArgsConstructor
public class TicketBookingController {
     Logger logger=LoggerFactory.getLogger(TicketBookingController.class);

    private final TicketBookingService ticketBookingService;

    @PostMapping("/bookTicket")
    public ResponseEntity<ServiceResponseDTO> bookTicket(HttpServletRequest request, @RequestBody TicketBookingDTO ticketBookingDTO){
        return new ResponseEntity<>(ticketBookingService.bookTicket(request, ticketBookingDTO), HttpStatus.CREATED);
    }
    @DeleteMapping("/cancelTicket")
    public ResponseEntity<ServiceResponseDTO> cancelTicket(HttpServletRequest request, @RequestParam("pnrNo") String pnrNo) throws ResourceNotFoundException {
        return new ResponseEntity<>(ticketBookingService.cancelTicket(request, pnrNo), HttpStatus.OK);
    }

    @GetMapping("/getTicketByPnrNo")
    public ResponseEntity<ServiceResponseDTO> getTicketDetailByPnrNo(HttpServletRequest request,@RequestParam("pnrNo")String pnrNo) throws ResourceNotFoundException {
        logger.info("get ticket detail by pnr is being executed ");
//        String pnr="1000120241115133736";
       return new ResponseEntity<>(ticketBookingService.getTicketDetailsByPnr(request, pnrNo),HttpStatus.OK);
    }
    @GetMapping("/getAllBookedTicketList")
    public ResponseEntity<ServiceResponseDTO> getAllBookedTicketList(HttpServletRequest request){
         return new ResponseEntity<>(ticketBookingService.getAllBookedTicket(request),HttpStatus.OK);
    }
    @GetMapping("/test")
    public ResponseEntity<ServiceResponseDTO> test(HttpServletRequest request){
        return new ResponseEntity<>(ServiceResponseDTO.builder().response("getting the response from the test method ").timestamp(Instant.now()).build(),HttpStatus.OK);
    }
    @GetMapping("/getAllCancelledTickets")
    public ResponseEntity<ServiceResponseDTO> getAllCancelledTicketForUser(HttpServletRequest request) throws ResourceNotFoundException {
        return new ResponseEntity<>(ticketBookingService.getAllCancelledTicketForAUser(request), HttpStatus.OK);

    }
}
