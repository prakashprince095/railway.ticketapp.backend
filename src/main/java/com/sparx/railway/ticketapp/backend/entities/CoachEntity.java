package com.sparx.railway.ticketapp.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.List;

@Entity(name="coach_table")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@SuperBuilder
@Setter
public class CoachEntity extends  BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String coachEntityId;

    private int trainNo;
    @ElementCollection
    @CollectionTable(name = "general_coach_details", joinColumns = @JoinColumn(name = "coach_table_id"))
    private List<CoachDetails> generalCoachDetails;
    @ElementCollection
    @CollectionTable(name = "sleeper_coach_details", joinColumns = @JoinColumn(name = "coach_table_id"))
    private List<CoachDetails> sleeperCoachDetails;
    @ElementCollection
    @CollectionTable(name = "first_ac_coach_details", joinColumns = @JoinColumn(name = "coach_table_id"))
    private List<CoachDetails> firstACCoachDetails;
    @ElementCollection
    @CollectionTable(name = "second_ac_coach_details", joinColumns = @JoinColumn(name = "coach_table_id"))
    private List<CoachDetails> secondACCoachDetails;
    @ElementCollection
    @CollectionTable(name = "third_ac_coach_details", joinColumns = @JoinColumn(name = "coach_table_id"))
    private List<CoachDetails> thirdACCoachDetails;




    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Getter
    @Setter
    public static class CoachDetails {

        private String coachId;
        private String coachType;
        private int totalNoOfSeat;




    }
}
