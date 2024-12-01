package com.sparx.railway.ticketapp.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TicketPriceRequestDTO {

    private String startStation;
    private String endStation;
    private String travelDate;
}
