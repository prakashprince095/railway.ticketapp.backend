package com.sparx.railway.ticketapp.backend.repository;

import com.sparx.railway.ticketapp.backend.entities.SeatToPassangerMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatToPassangerMappingEntityRepository  extends JpaRepository<SeatToPassangerMappingEntity,String> {
    @Query("SELECT s FROM  seat_to_passanger_mapping_table s WHERE s.pnrNo =:pnrNo")
    List<SeatToPassangerMappingEntity> findListByPnrNo(@Param("pnrNo") String pnrNo);
}
