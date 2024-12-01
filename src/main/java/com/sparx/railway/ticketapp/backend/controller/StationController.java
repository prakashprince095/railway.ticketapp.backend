package com.sparx.railway.ticketapp.backend.controller;

import com.sparx.railway.ticketapp.backend.Exception.ResourceNotFoundException;
import com.sparx.railway.ticketapp.backend.dto.AddTrainListToStationDTO;
import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import com.sparx.railway.ticketapp.backend.dto.StationEntityDTO;
import com.sparx.railway.ticketapp.backend.service.StationEntityService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/station")
@RequiredArgsConstructor
public class StationController {

    private final StationEntityService stationEntityService;

    @PostMapping("/create")
    public ResponseEntity<ServiceResponseDTO> createStation(HttpServletRequest request, @RequestBody  StationEntityDTO stationEntityDTO){
        return new ResponseEntity<>(stationEntityService.createSation(request, stationEntityDTO), HttpStatus.CREATED);
    }
    @PutMapping("update")
    public ResponseEntity<ServiceResponseDTO> updateStation(HttpServletRequest request, @RequestParam("stationId") String stationId, @RequestBody StationEntityDTO station) throws ResourceNotFoundException {
        return new ResponseEntity<>(stationEntityService.updateStation(request, stationId, station), HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ServiceResponseDTO> deleteStation(@RequestParam("stationId") String stationId) throws ResourceNotFoundException {
        return new ResponseEntity<>(stationEntityService.deleteStation(stationId), HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity<ServiceResponseDTO> getStationById(@RequestParam("stationId") String stationId) throws ResourceNotFoundException {
        return new ResponseEntity<>(stationEntityService.getStation(stationId), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<ServiceResponseDTO> getAllStationEntities(HttpServletRequest request,@RequestParam("pageNumber")int pageNumber,@RequestParam("recordsPerPage")int recordsPerPage){
        return new ResponseEntity<>(stationEntityService.getAllStation(pageNumber,recordsPerPage), HttpStatus.OK);
    }
    @PostMapping("/addTrainList")
    public ResponseEntity<ServiceResponseDTO> addTrainListToStation(HttpServletRequest request,@RequestParam("station_id") String stationId , @RequestBody AddTrainListToStationDTO trainList) throws ResourceNotFoundException {
     return new ResponseEntity<>(stationEntityService.addTrainListToStationEntity(request,stationId, trainList),HttpStatus.CREATED);
    }
}
