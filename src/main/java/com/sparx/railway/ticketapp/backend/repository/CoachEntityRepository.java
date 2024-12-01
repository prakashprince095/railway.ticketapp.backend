package com.sparx.railway.ticketapp.backend.repository;

import com.sparx.railway.ticketapp.backend.entities.CoachEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoachEntityRepository extends JpaRepository<CoachEntity,String> {
    Optional<CoachEntity> findByTrainNo(int trainNo);

}
