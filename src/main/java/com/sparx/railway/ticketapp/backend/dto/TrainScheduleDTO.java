package com.sparx.railway.ticketapp.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparx.railway.ticketapp.backend.entities.TrainSchedule;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainScheduleDTO {
    @JsonProperty("train_no")
    private String trainNo;
    @JsonProperty("schedules")
    List<Schedules> schedules = new ArrayList<Schedules>();


    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Schedules{
        @JsonProperty("station_id")
        private String stationId;
        @JsonProperty("arrival_time")
        private String arrivalTime;
        @JsonProperty("departure_time")
        private String departureTime;
    }
}
