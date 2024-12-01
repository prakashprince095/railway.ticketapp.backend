package com.sparx.railway.ticketapp.backend.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class PNRGenratorUtil {

    private final TokenUtil tokenUtil;



    public StringBuilder buildPNRNO(int trainNo) {
        // Use StringBuilder instead of String for efficient concatenation
        StringBuilder resultantNo = new StringBuilder();
        String timeStamp = String.valueOf(Instant.now());

        // Using append() method of StringBuilder for concatenation
        resultantNo.append(trainNo)
                .append(timeStamp.substring(0, 4))  // Year
                .append(timeStamp.substring(5, 7))  // Month
                .append(timeStamp.substring(8, 10)) // Day
                .append(timeStamp.substring(11, 13)) // Hour
                .append(timeStamp.substring(14, 16)) // Minute
                .append(timeStamp.substring(17, 19)); // Second

        // Printing the final PNR value
        System.out.println("The PNR value is: " + resultantNo.toString());
        return resultantNo;
    }

}
