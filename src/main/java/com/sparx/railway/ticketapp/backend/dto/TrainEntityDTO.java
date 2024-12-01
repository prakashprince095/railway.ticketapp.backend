package com.sparx.railway.ticketapp.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainEntityDTO {
    @JsonProperty("train_no")
    private Integer trainNo;
    @JsonProperty("train_name")
    private String trainName;
    @JsonProperty("start_station")
    private String startStation;
    @JsonProperty("end_station")
    private String endStation;
    @JsonProperty("start_time")
    private String startTime;
    @JsonProperty("end_time")
    private String endTime;
}
