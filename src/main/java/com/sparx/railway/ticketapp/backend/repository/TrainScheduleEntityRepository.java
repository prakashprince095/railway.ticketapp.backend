package com.sparx.railway.ticketapp.backend.repository;

import com.sparx.railway.ticketapp.backend.entities.TrainSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainScheduleEntityRepository extends JpaRepository<TrainSchedule,String> {

//    List<Object[]>
}
