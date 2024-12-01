package com.sparx.railway.ticketapp.backend.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketPriceDTO {


    private String startStation;
    private String endStation;
    private int totalDistance;
    private List<TicketPrice> ticketPriceList;



    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TicketPrice    {
        private int trainNo;
        private String classType;
        private double price;
        private String noOfTicketAvailable;

    }

}
