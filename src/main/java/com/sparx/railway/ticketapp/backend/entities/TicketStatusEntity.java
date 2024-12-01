package com.sparx.railway.ticketapp.backend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity(name="ticket_status_entity")
public class TicketStatusEntity  extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ticketStatusId;
    @Column(name="train_no")
    private int trainNo;
    @Column(name="total_general_coach")
    private int totalGeneralCoach;
    @Column(name="total_available_general_ticket")
    private int totalAvailableGeneralTicket;
    @Column(name="total_booked_general_ticket")
    private int totalBookedGeneralTicket;
    @Column(name="general_booked_seat_no_list")
    private List<String > generalBookedSeatNoList;
    @Column(name="total_sleeper_coach")
    private int totalSleeperCoach;
    @Column(name="total_availble_sleeper_ticket")
    private int totalAvailableSleeperTicket;
    @Column(name="total_booked_sleeper_ticket")
    private int totalBookedSleeperTicket;
    @Column(name="sleeper_booked_seat_no_list")
    private List<String > sleeperBookedSeatNoList;
    @Column(name="total_first_ac_coach")
    private int totalFirstAcCoach;
    @Column(name="total_available_first_ticket")
    private int totalAvailableFirstACTicket;
    @Column(name="total_booked_first_ac_ticket")
    private int totalBookedFirstACTicket;
    @Column(name="first_ac_booked_seat_no_list")
    private List<String > firstACBookedSeatNoList;
    @Column(name="total_second_ac_coach")
    private int totalSecondACCoach;
    @Column(name="total_available_second_ac_ticket")
    private int totalAvailableSecondACTicket;
    @Column(name="total_booked_second_ac_ticket")
    private int totalBookedSecondACTicket;
    @Column(name="second_ac_booked_seat_no_list")
    private List<String > secondACBookedSeatNoList;
    @Column(name="total_third_ac_coach")
    private int totalThirdACCoach;
    @Column(name="total_available_third_ac_ticket")
    private int totalAvailableThirdACTicket;
    @Column(name="total_booked_third_ac_ticket")
    private int totalBookedThirdACTicket;
    @Column(name="third_ac_booked_seat_no_list")
    private List<String > thirdACBookedSeatNoList;
    @Column(name="start_date_of_the_journey")
    private Date startDateofTheJourney;
    @Column(name="end_date_of_the_journey")
    private Date endDateofTheJourney;



}
