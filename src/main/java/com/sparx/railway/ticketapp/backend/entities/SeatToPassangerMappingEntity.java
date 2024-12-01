package com.sparx.railway.ticketapp.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.util.Date;

@Getter
@Entity(name="seat_to_passanger_mapping_table")
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SeatToPassangerMappingEntity extends  BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String seatMappingId;
    @Column(name="seat_number")
    private String seatNumber;
    @Column(name="coach_no")
    private String coachNo;
    @Column(name="pnr_no")
    private String pnrNo;
    @Column(name="train_no")
    private int trainNo;
    @Column(name="date_of_travel")
    private Date dateOfTravel;
}
