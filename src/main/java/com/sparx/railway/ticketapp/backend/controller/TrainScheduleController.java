package com.sparx.railway.ticketapp.backend.controller;

import com.sparx.railway.ticketapp.backend.Exception.ResourceNotFoundException;
import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import com.sparx.railway.ticketapp.backend.dto.TrainScheduleDTO;
import com.sparx.railway.ticketapp.backend.service.TrainScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/train-schedule")
@RequiredArgsConstructor
public class TrainScheduleController {

    private final TrainScheduleService trainScheduleService;

    @PostMapping("/create")
    public ResponseEntity<ServiceResponseDTO> createTrainSchedule(HttpServletRequest request, @RequestBody TrainScheduleDTO trainScheduleDTO){
        return new ResponseEntity<>(trainScheduleService.createTrainSchedule(request, trainScheduleDTO), HttpStatus.CREATED);

    }

    @PutMapping("/update")
    public ResponseEntity<ServiceResponseDTO> updateTrainSchedule(HttpServletRequest request, @RequestBody TrainScheduleDTO trainScheduleDTO, @RequestParam("trainScheduleId") String trainScheduleId) throws ResourceNotFoundException {
        return new ResponseEntity<>(trainScheduleService.updateTrainSchedule(request, trainScheduleId, trainScheduleDTO), HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ServiceResponseDTO> deleteTrainSchedule(@RequestParam("trainScheduleId") String trainScheduleId) throws ResourceNotFoundException{
        return new ResponseEntity<>(trainScheduleService.deleteTrainSchedule(trainScheduleId), HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity<ServiceResponseDTO> getTrainScheduleById(@RequestParam("trainScheduleId") String trainScheduleId) throws ResourceNotFoundException{
     return new ResponseEntity<>(trainScheduleService.getTrainScheduleById(trainScheduleId),HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<ServiceResponseDTO> getAllTrainSchedules(HttpServletRequest request, @RequestParam("pageNumber") int pageNumber, @RequestParam("recordsPerPage") int recordsPerPage){
        return new ResponseEntity<>(trainScheduleService.getAllTrainSchedules(), HttpStatus.OK);
    }
}
