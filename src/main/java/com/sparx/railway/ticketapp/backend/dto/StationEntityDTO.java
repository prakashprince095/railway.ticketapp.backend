package com.sparx.railway.ticketapp.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StationEntityDTO {
    @JsonProperty("station_name")
    private String stationName;
    @JsonProperty("distance")
    private String distance;
    @JsonProperty("station_code")
    private String stationCode;
    @JsonProperty("state_name")
    private String stateName;
    @JsonProperty("city_name")
    private String cityName;
    @JsonProperty("country_name")
    private String countryName;
    @JsonProperty("pin_code")
    private String pinCode;
    @JsonProperty("train_no_list")
    private List<Integer> trainNoList;

}
