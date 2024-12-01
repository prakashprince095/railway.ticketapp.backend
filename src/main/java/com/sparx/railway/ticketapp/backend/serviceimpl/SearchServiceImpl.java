package com.sparx.railway.ticketapp.backend.serviceimpl;

import com.sparx.railway.ticketapp.backend.constant.DistanceConstant;
import com.sparx.railway.ticketapp.backend.dto.SearchTrainByTrainNoDTO;
import com.sparx.railway.ticketapp.backend.dto.SearchTrainScheduleResponseDTO;
import com.sparx.railway.ticketapp.backend.dto.ServiceResponseDTO;
import com.sparx.railway.ticketapp.backend.entities.TrainEntity;
import com.sparx.railway.ticketapp.backend.entities.TrainSchedule;
import com.sparx.railway.ticketapp.backend.repository.CoachEntityRepository;
import com.sparx.railway.ticketapp.backend.repository.TrainEntityRepository;
import com.sparx.railway.ticketapp.backend.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final TrainEntityRepository trainEntityRepository;
    @Override
    public ServiceResponseDTO searchTrainByTrainNoAlongWithSchedule(int trainNo) {
        try{
            List<Object[]> response =trainEntityRepository.findByTrainNoWithSchedules(trainNo);
            SearchTrainByTrainNoDTO responseOfSearch = new SearchTrainByTrainNoDTO();
            List<SearchTrainByTrainNoDTO.TrainScheduleTimeOnStation> schedule=new ArrayList<>();
            Object[] obj=response.get(0);
            int count =0;
            String trainName=(String)obj[1];
            for(Object[] result : response){

                if(count==0){
                    responseOfSearch.setTrainNo(Integer.parseInt((String) result[0]));
                    responseOfSearch.setTrainName((String) result[1]);
                    responseOfSearch.setStartStation((String) result[2]);
                    responseOfSearch.setEndStation((String) result[3]);
                    count++;
                }
                SearchTrainByTrainNoDTO.TrainScheduleTimeOnStation scheduleTimeOnStation=new SearchTrainByTrainNoDTO.TrainScheduleTimeOnStation();
                scheduleTimeOnStation.setStationName((String) result[4]);
                Object value = result[6];  // This is your object from the query result

                // Get the class type (data type) of the object
//                Class<?> clazz = value.getClass();
//
//                // Print the class type name
//                System.out.println("Data type of result[6] is: " + clazz.getName());

                // Convert arrival and departure times from Timestamp to ZonedDateTime
//                Timestamp arrivalTimestamp = (Timestamp) result[6];
//                Timestamp departureTimestamp = (Timestamp) result[7];

                // Convert Timestamp to ZonedDateTime (using system default zone for conversion)
//                ZonedDateTime arrivalTime = convertToZonedDateTime(arrivalTimestamp);
//                ZonedDateTime departureTime = convertToZonedDateTime(departureTimestamp);
                ZonedDateTime arrivalTime=convertTimeStampToZonedDateTime((Timestamp) result[6]);
                String arrivalTimeInHours = arrivalTime.format(DateTimeFormatter.ofPattern("HH:mm"));
                ZonedDateTime depatureTime=convertTimeStampToZonedDateTime((Timestamp) result[7]);
                String depatureTimeInHours = depatureTime.format(DateTimeFormatter.ofPattern("HH:mm"));
                scheduleTimeOnStation.setArrivalTime(arrivalTimeInHours);
                scheduleTimeOnStation.setDepartureTime(depatureTimeInHours);
                String dateTime = arrivalTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd , HH:mm"));
                scheduleTimeOnStation.setDate(dateTime);
                int distance=(Integer) result[8];
                if(trainName.substring(trainName.length()-4).equalsIgnoreCase("down")){
//                    System.out.println("the train is a down train ");
                      int distanceToBeShown= DistanceConstant.DISTANCE_CONSTANT_FOR_PATNA_TO_DELHI-distance;
                    scheduleTimeOnStation.setDistance(distanceToBeShown);
                }else{
//                    System.out.println("the train is a up train ");
                    scheduleTimeOnStation.setDistance(distance);

                }
                schedule.add(scheduleTimeOnStation);
            }
            responseOfSearch.setTrainScheduleTimeOnStation(schedule);
            return ServiceResponseDTO.builder().response(responseOfSearch)
                    .timestamp(Instant.now())
                    .code(HttpStatus.OK.value())
                    .status(HttpStatus.OK.value())
                    .build();
        }catch(Exception ex){
            return ServiceResponseDTO.builder().response(ex.getMessage()).build();

        }

    }

    // Helper method to convert Timestamp to ZonedDateTime
//    private ZonedDateTime convertToZonedDateTime(Timestamp timestamp) {
////        return timestamp.toInstant().atZone(ZoneId.systemDefault()); // Use system default timezone
//        return ZonedDateTime.ofInstant(timestamp.getTimestamp().toInstant(), ZoneId.of("UTC")); // Assuming UTC timezone for conversion
//    }


    private ZonedDateTime convertTimeStampToZonedDateTime(Timestamp timestamp) {
        // Convert Timestamp to Instant
        Instant instant = timestamp.toInstant();

        // Convert Instant to ZonedDateTime with a specific time zone (e.g., "Asia/Kolkata")
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Asia/Kolkata"));

        // Return the ZonedDateTime
        return zonedDateTime;
    }
}
