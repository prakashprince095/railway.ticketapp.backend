package com.sparx.railway.ticketapp.backend.controller;

import com.sparx.railway.ticketapp.backend.Exception.ResourceNotFoundException;
import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import com.sparx.railway.ticketapp.backend.dto.TrainEntityDTO;
import com.sparx.railway.ticketapp.backend.service.TrainEntityService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/train")
@RequiredArgsConstructor
public class TrainController {

    private final TrainEntityService trainEntityService;

    @PostMapping("/create")
    public ResponseEntity<ServiceResponseDTO> createTrainEntity(HttpServletRequest request, @RequestBody TrainEntityDTO trainEntityDTO){
    return new ResponseEntity<>(trainEntityService.cretaeTrainEntity(request, trainEntityDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ServiceResponseDTO> updateTrainEntity(HttpServletRequest request,@RequestParam("trainId") String trainId, @RequestBody TrainEntityDTO trainEntityDTO) throws ResourceNotFoundException {
        return new ResponseEntity<>(trainEntityService.updateTrainEntity(request,trainId,trainEntityDTO),HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ServiceResponseDTO> deleteResponseEntity(@RequestParam("trainId")String trainId) throws ResourceNotFoundException {
        return new ResponseEntity<>(trainEntityService.deleteTrainEntity(trainId),HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity<ServiceResponseDTO> getTrainEntityById(HttpServletRequest request, @RequestParam("trainId") String trainId) throws ResourceNotFoundException {
        return new ResponseEntity<>(trainEntityService.getTrainEntityById(trainId),HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<ServiceResponseDTO> getAllTrainEntities(HttpServletRequest request){
        return new ResponseEntity<>(trainEntityService.getAllTrainEntities(), HttpStatus.OK);
    }

}
