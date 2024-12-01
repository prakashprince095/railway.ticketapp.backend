package com.sparx.railway.ticketapp.backend.service;

import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;

public interface SearchService {
    public ServiceResponseDTO searchTrainByTrainNoAlongWithSchedule(int trainNo);
}
