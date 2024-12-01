package com.sparx.railway.ticketapp.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDTO {

    private Instant timestamp;

    private Integer status;

    private int code;

    private Object response;

    private int totalNoOfRecords;
}
