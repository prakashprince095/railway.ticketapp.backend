package com.sparx.railway.ticketapp.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trains")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TrainEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name="train_no")
    private Integer trainNo;
    @Column(name="route_type")
    private String routeType;
    @Column(name="train_name")
    private String trainName;
    @Column(name="start_station")
    private String startStation;
    @Column(name="end_station")
    private String endStation;
    @Column(name="start_time")
    private String startTime;
    @Column(name="end_time")
    private String endTime;
    @Column(name="facility_factor")
    private double facilityFactor;
    @Column(name="station_code_list")
    private List<String> stationCodeList = new ArrayList<String>();

}
