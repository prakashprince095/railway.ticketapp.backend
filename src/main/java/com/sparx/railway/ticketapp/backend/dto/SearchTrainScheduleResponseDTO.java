package com.sparx.railway.ticketapp.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SearchTrainScheduleResponseDTO {

    private String trainName;
    private String startStation;
    private String endStation;
    private String stationName;
    private String trainNo;
    private String stationId;
    private ZonedDateTime arrivalTime;
    private ZonedDateTime departureTime;

    // Constructor
    public SearchTrainScheduleResponseDTO(String trainName, String startStation, String endStation,
                                   String stationName, String trainNo, String stationId,
                                   ZonedDateTime arrivalTime, ZonedDateTime departureTime) {
        this.trainName = trainName;
        this.startStation = startStation;
        this.endStation = endStation;
        this.stationName = stationName;
        this.trainNo = trainNo;
        this.stationId = stationId;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }


    // Getters and Setters
    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public ZonedDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(ZonedDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public ZonedDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(ZonedDateTime departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public String toString() {
        return "TrainScheduleDTO{" +
                "trainName='" + trainName + '\'' +
                ", startStation='" + startStation + '\'' +
                ", endStation='" + endStation + '\'' +
                ", stationName='" + stationName + '\'' +
                ", trainNo='" + trainNo + '\'' +
                ", stationId='" + stationId + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                '}';
    }
}
