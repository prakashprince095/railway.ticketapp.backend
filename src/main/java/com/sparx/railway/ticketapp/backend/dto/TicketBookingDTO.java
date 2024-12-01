package com.sparx.railway.ticketapp.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparx.railway.ticketapp.backend.entities.TicketEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TicketBookingDTO {
    private String coachType;
    @JsonProperty("start_date_of_journey")
    private String startDateOfJourney;
    @JsonProperty("end_date_of_journey")
    private String endDateOfJourney;
    @JsonProperty("journey_start_station")
    private String journeyStartStation;
    @JsonProperty("journey_end_station")
    private String journeyEndStation;
//    @JsonProperty("ticket_price")
//    private double ticketPrice;
    @JsonProperty("train_no")
    private int trainNo;
//    @JsonProperty("seat_no")
//    private String seatNo;
//    @JsonProperty("coach_no")
//    private String coachNo;
    @JsonProperty("email_id")
    private String emailId;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("passanger_details")
    private List<PassangerDetail> passangerDetails = new ArrayList<PassangerDetail>();

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Data
    public static class PassangerDetail{
        @JsonProperty("passanger_name")
        private String passangerName;
        @JsonProperty("age")
        private int age;
        @JsonProperty("gender")
        private String gender;
        @JsonProperty("contact_number")
        private String contactNumber;
        @JsonProperty("email_id")
        private String emailId;
        @JsonProperty("date_of_birth")
        private Date dateOfBirth;


    }
}
