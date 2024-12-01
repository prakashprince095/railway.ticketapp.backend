package com.sparx.railway.ticketapp.backend.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class TimeandDateUtil {

    public ZonedDateTime convertDateStringToZonedDateTime(String dateTimeString){
       // String dateTimeString = "2024-11-05 14:30:00 +0100"; // Date-time string in custom format

        // Define the custom format pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");

        // Parse the string into a ZonedDateTime
        ZonedDateTime parsedDateTime = ZonedDateTime.parse(dateTimeString, formatter);

        System.out.println("Parsed ZonedDateTime: " + parsedDateTime);
        return parsedDateTime;

    }
    public Date setTravelDate(String dateStr) throws ParseException {
        // Define the desired date format
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        // Parse the string into a Date object
        return formatter.parse(dateStr);
    }


}
