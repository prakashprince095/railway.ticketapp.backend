package com.sparx.railway.ticketapp.backend.entities;

import com.sparx.railway.ticketapp.backend.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.Date;


@Entity(name="passanger_details")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@SuperBuilder
@ToString(exclude = {"ticketEntity"}) // Exclude ticketEntity from toString method for better readability
@EqualsAndHashCode(exclude = {"ticketEntity"}) // Exclude ticketEntity from equalsAndHashCode method for better performance
@Table(name="passanger_details_table")
//@Inheritance(strategy = InheritanceType.JOINED) // Use JOINED inheritance strategy
public class PassangerDetailEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String passangerId;
    @Column(name="passanger_name")
    private String passangerName;
    @Column(name="age")
    private int age;
    @Column(name="seat_no")
    private String seatNo;
    @Column(name="coach_no")
    private String coachNo;
    @Column(name="gender")
    private String gender;
    @Column(name="contact_number")
    private String contactNumber;
    @Column(name="email_id")
    private String emailId;
    @Column(name="date_of_birth")
    private Date dateOfBirth;
    @Column(name="pnr_no")
    private String pnrNo;
    @ManyToOne
    @JoinColumn(name = "ticket_id") // Foreign key column that references TicketEntity
    private TicketEntity ticketEntity;
    @Column(name="ticket_status")
    @Enumerated(EnumType.STRING) // Use EnumType.STRING to store the enum values as strings in the database
    private TicketStatus ticketStatus;
    @PrePersist
    public void setDefaultStatus() {
        if (this.ticketStatus == null) {
            this.ticketStatus = TicketStatus.BOOKED;  // Set default value if status is null
        }
    }

}
