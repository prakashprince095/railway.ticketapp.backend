package com.sparx.railway.ticketapp.backend.repository;

import com.sparx.railway.ticketapp.backend.entities.PassangerDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassangerDetailEntityRepository extends JpaRepository<PassangerDetailEntity,String> {
    @Query("SELECT p FROM passanger_details p WHERE p.pnrNo = :pnrNo")
    List<PassangerDetailEntity> fetchThePassangerListByPnr(@Param("pnrNo") String pnrNo);

}
