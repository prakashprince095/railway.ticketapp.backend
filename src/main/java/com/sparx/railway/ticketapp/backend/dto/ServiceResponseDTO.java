package com.sparx.railway.ticketapp.backend.dto;

import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ServiceResponseDTO {

    private Instant timestamp;

    private Integer status;

    private int code;

    private Object response;

}
