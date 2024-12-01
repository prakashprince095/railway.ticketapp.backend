package com.sparx.railway.ticketapp.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.Date;
import java.util.List;

@Entity(name="ticket_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TicketEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="ticket_id")
    private String ticketId;
    @Column(name="pnr_No")
    private String pnrNo;
    @Column(name="start_date_of_journey")
    private Date startDateOfJourney;
    @Column(name="end_date_of_journey")
    private Date endDateOfJourney;
    @Column(name="journey_start_station")
    private String journeyStartStation;
    @Column(name="journey_end_station")
    private String journeyEndStation;
    @Column(name="ticket_price")
    private double ticketPrice;
    @Column(name="train_no")
    private int trainNo;
    @Column(name="is_cancelled")
    private boolean isCancelled;
    @Column(name="account_email_id")
    private String accountEmailId;
    @Column(name="email_id")
    private String emailId;
    @Column(name="phone_number")
    private String phoneNumber;
    @OneToMany(mappedBy = "ticketEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<PassangerDetailEntity> passangerDetailEntity;

}
