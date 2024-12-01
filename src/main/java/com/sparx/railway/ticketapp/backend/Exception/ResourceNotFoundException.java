package com.sparx.railway.ticketapp.backend.Exception;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResourceNotFoundException extends Exception{
    private int status;
    private int code;
    private String message;
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
