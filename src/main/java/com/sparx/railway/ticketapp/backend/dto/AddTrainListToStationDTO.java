package com.sparx.railway.ticketapp.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddTrainListToStationDTO {
    List<Integer> trainList = new ArrayList<Integer>();
}
