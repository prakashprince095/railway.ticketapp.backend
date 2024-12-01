package com.sparx.railway.ticketapp.backend.controller;

import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import com.sparx.railway.ticketapp.backend.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

      private final SearchService searchService;
      @GetMapping(path = "/train")  // Endpoint to search train by train number
      public ResponseEntity<ServiceResponseDTO> searchTrainByTrainNo(@RequestParam("trainNo") int trainNo){
          return new ResponseEntity<>(searchService.searchTrainByTrainNoAlongWithSchedule(trainNo), HttpStatus.OK);  // Return the response from the service
      }

}
