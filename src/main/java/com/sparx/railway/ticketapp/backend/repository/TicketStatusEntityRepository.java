package com.sparx.railway.ticketapp.backend.repository;

import com.sparx.railway.ticketapp.backend.entities.TicketStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TicketStatusEntityRepository extends JpaRepository<TicketStatusEntity,String> {

    @Query("SELECT t FROM ticket_status_entity t WHERE t.trainNo = :trainNo AND t.startDateofTheJourney = :date")
    Optional<TicketStatusEntity> findTicketStatusEntityByTrainNoAndDate(@Param("trainNo") int trainNo, @Param("date") Date date);


}
