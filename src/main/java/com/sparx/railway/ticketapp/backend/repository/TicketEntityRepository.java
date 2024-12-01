package com.sparx.railway.ticketapp.backend.repository;

import com.sparx.railway.ticketapp.backend.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TicketEntityRepository extends JpaRepository<TicketEntity,String> {
    Optional<TicketEntity> findByPnrNo(String pnr);
//    @Query("SELECT tt FROM ticket_table tt WHERE tt.accountEmailId = :accountEmailId")
//    List<TicketEntity> findAllTicketEntityByAccountId(@Param("account-email_id") String accountEmailId);
    @Query("SELECT tt FROM ticket_table tt WHERE tt.accountEmailId = :accountEmailId AND tt.isCancelled = :isCancelled")
    List<TicketEntity> findAllCancelledTicketEntityByAccountId(@Param("accountEmailId") String accountEmailId ,boolean isCancelled);
    @Query("SELECT tt FROM ticket_table tt WHERE tt.accountEmailId = :accountEmailId AND tt.isCancelled = :isCancelled AND tt.startDateOfJourney > :todaysDate")
    List<TicketEntity> findUpcomingBookedTicket(@Param("accountEmailId") String accountEmailId, @Param("isCancelled") boolean isCancelled, @Param("todaysDate") Date todaysDate);

}
