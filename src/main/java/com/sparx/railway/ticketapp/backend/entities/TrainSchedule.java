package com.sparx.railway.ticketapp.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "train_station_time_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TrainSchedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "train_no")
    private String trainNo;

    @ElementCollection
    @CollectionTable(name = "train_schedule_schedules", joinColumns = @JoinColumn(name = "train_schedule_id"))
    private List<Schedules> schedules;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Schedules {

        @Column(name = "station_id")
        private String stationId;

        @Column(name = "arrival_time")
        private ZonedDateTime arrivalTime;

        @Column(name = "departure_time")
        private ZonedDateTime departureTime;

        // Constructors, getters, setters...
    }
}
