package com.sparx.railway.ticketapp.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketStatusEntityDTO {
    @JsonProperty("train_no")
    private int trainNo;
    @JsonProperty("start_date_of_journey")
    private String startDateofJourney;
    @JsonProperty("end_date_of_journey")
    private String endDateOfJourney;
    @JsonProperty("total_general_coach")
    private int totalGeneralCoach;
    @JsonProperty("total_sleeper_coach")
    private int totalSleeperCoach;
    @JsonProperty("total_first_ac_coach")
    private int totalFirstACCoach;
    @JsonProperty("total_second_ac_coach")
    private int totalSecondACCoach;
    @JsonProperty("total_third_ac_coach")
    private int totalThirdACCoach;




}
