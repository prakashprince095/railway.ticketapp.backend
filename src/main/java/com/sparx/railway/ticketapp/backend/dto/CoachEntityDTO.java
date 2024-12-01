package com.sparx.railway.ticketapp.backend.dto;

import com.sparx.railway.ticketapp.backend.entities.CoachEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class CoachEntityDTO {
    private int trainNo;
    private List<CoachDetails> generalCoachDetails;
    private List<CoachDetails> sleeperCoachDetails;
    private List<CoachDetails> firstACCoachDetails;
    private List<CoachDetails> secondACCoachDetails;
    private List<CoachDetails> thirdACCoachDetails;





    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Getter
    @Setter
    private static class CoachDetails {

        private String coachId;
        private String coachType;
        private int totalNoOfSeat;




    }
}
