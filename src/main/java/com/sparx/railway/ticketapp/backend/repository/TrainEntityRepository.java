package com.sparx.railway.ticketapp.backend.repository;

import com.sparx.railway.ticketapp.backend.dto.SearchTrainByTrainNoDTO;
import com.sparx.railway.ticketapp.backend.dto.SearchTrainScheduleResponseDTO;
import com.sparx.railway.ticketapp.backend.entities.TrainEntity;
import com.sparx.railway.ticketapp.backend.entities.TrainSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainEntityRepository extends JpaRepository<TrainEntity,String> {
//    @Query("SELECT ts FROM TrainSchedule ts JOIN FETCH ts.schedules s WHERE ts.trainNo = :trainNo")
//    List<TrainSchedule> findByTrainNoWithSchedules(@Param("trainNo") Integer trainNo);
@Query(value = "SELECT tstt.train_no, t.train_name ,t.start_station,t.end_station ,st.station_name ,  s.station_id, s.arrival_time,s.departure_time,st.distance from ticketapp.train_station_time_table tstt JOIN \n" +
        "    ticketapp.train_schedule_schedules s \n" +
        "    ON tstt.id = s.train_schedule_id\n" +
        "    JOIN \n" +
        "    ticketapp.station_table st on s.station_id =st.station_id \n" +
        "    JOIN \n" +
        "    ticketapp.trains t on tstt.train_no = t.train_no \n" +
        "WHERE \n" +
        "    tstt.train_no = :trainNo;",nativeQuery = true)
List<Object[]> findByTrainNoWithSchedules(@Param("trainNo") Integer trainNo);

//    @Query("SELECT new com.sparx.railway.ticketapp.backend.dto.SearchTrainScheduleResponseDTO(" +
//            "t.trainName, t.startStation, t.endStation, st.stationName, " +
//            "tstt.trainNo, s.stationId, s.arrivalTime, s.departureTime) " +
//            "FROM TrainStationTimeTable tstt " +
//            "JOIN TrainScheduleSchedules s ON tstt.id = s.trainScheduleId " +
//            "JOIN StationTable st ON s.stationId = st.stationId " +
//            "JOIN Trains t ON tstt.trainNo = t.trainNo " +
//            "WHERE tstt.trainNo = :trainNo")
//    List<SearchTrainScheduleResponseDTO> findByTrainNoWithSchedules(@Param("trainNo") Integer trainNo);

     Optional<TrainEntity> findByTrainNo(int trainNo);
}
