package com.sparx.railway.ticketapp.backend.dto;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchTrainByTrainNoDTO {

    private String trainName;
    private String startStation;
    private String endStation;
    private int trainNo;
    List<TrainScheduleTimeOnStation> trainScheduleTimeOnStation=new ArrayList<>();

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class TrainScheduleTimeOnStation{
        private String Date;
        private String arrivalTime;
        private String  departureTime;
        private String stationName;
        private Integer distance;
    }
}
