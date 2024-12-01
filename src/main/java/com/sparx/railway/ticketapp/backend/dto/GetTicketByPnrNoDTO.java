package com.sparx.railway.ticketapp.backend.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GetTicketByPnrNoDTO {
    private String pnrNo;
    private String journeyStartDate;
    private String journeyEndDate;
    private String trainName;
    private int trainNo;
    private String price ;
    private String ticketBookedDate;
    private String status;
    private String ticketClass;
    private List<PassangerDetails> passangerDetailsList;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PassangerDetails{
        private String passangerName;
        private int passagerAge;
        private String gender;
        private String seatNo;
        private String coachNo;

    }
}
