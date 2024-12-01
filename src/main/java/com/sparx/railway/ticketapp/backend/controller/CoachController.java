package com.sparx.railway.ticketapp.backend.controller;

import com.sparx.railway.ticketapp.backend.Exception.ResourceNotFoundException;
import com.sparx.railway.ticketapp.backend.dto.CoachEntityDTO;
import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import com.sparx.railway.ticketapp.backend.dto.TicketStatusEntityDTO;
import com.sparx.railway.ticketapp.backend.service.CoachService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/coach")
@RequiredArgsConstructor
public class CoachController {

    private final CoachService coachService;
    @PostMapping("/create")
    public ResponseEntity<ServiceResponseDTO> createCoachEntity(HttpServletRequest request, @RequestBody CoachEntityDTO coachEntityDTO){
        return new ResponseEntity<>(coachService.addCoach(request, coachEntityDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ServiceResponseDTO> updateCoachEntity(HttpServletRequest request, @RequestBody CoachEntityDTO coachEntityDTO , String coachEntityId){
        return new ResponseEntity<>(coachService.updateCoach(request, coachEntityId, coachEntityDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ServiceResponseDTO> deleteCoachEntity(HttpServletRequest request, String coachEntityId) throws ResourceNotFoundException {
        return new ResponseEntity<>(coachService.deleteCoach(request, coachEntityId), HttpStatus.OK);
    }
    @GetMapping("/getById")
    public ResponseEntity<ServiceResponseDTO> findCoachEntityById(HttpServletRequest request,@RequestParam("coachEntityId") String coachEntityId) throws ResourceNotFoundException {
        return new ResponseEntity<>(coachService.getCoachById(request, coachEntityId), HttpStatus.OK);
    }

    @GetMapping("/getByTrainNo")
    public ResponseEntity<ServiceResponseDTO> findCoachEntityByTrainNo(HttpServletRequest request,@RequestParam("trainNo") int trainNo) throws ResourceNotFoundException {
        return new ResponseEntity<>(coachService.getCoachByTrainNo(request, trainNo), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<ServiceResponseDTO> getAllCoachEntities( HttpServletRequest request,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "recordPerPage", required = false, defaultValue = "5") int recordPerPage) {
        return new ResponseEntity<>(coachService.getAllCoaches(request,pageNumber, recordPerPage), HttpStatus.OK);
    }

    @PostMapping("/assignCoachToTrain")
    public ResponseEntity<ServiceResponseDTO> assignCoachToTrain(HttpServletRequest request, @RequestBody TicketStatusEntityDTO ticketStatusEntityDTO) throws ParseException {
        return new ResponseEntity<>(coachService.assignCoachToTrain(request, ticketStatusEntityDTO), HttpStatus.CREATED);

    }


}
