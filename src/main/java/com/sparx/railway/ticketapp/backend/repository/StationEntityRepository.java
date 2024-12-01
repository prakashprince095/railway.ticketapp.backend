package com.sparx.railway.ticketapp.backend.repository;

import com.sparx.railway.ticketapp.backend.entities.StationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StationEntityRepository extends JpaRepository<StationEntity,String> {
    @Query("SELECT s FROM StationEntity s WHERE s.stationName = :stationName")
    Optional<StationEntity> findByStationName(String stationName);
}
