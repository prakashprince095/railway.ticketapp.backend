package com.sparx.railway.ticketapp.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="station_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@AttributeOverride(name = "id", column = @Column(name = "station_id"))
public class StationEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String stationId;
    @Column(name="serial_no")
    private int serialNo;
    @Column(name="station_name")
    private String stationName;
    @Column(name="distance")
    private int distance;
    @Column(name="station_code",unique = true)
    private String stationCode;
    @Column(name="state_name")
    private String stateName;
    @Column(name="city_name")
    private String cityName;
    @Column(name="country_name")
    private String countryName;
    @Column(name="pin_code",unique = true)
    private String pinCode;
    @Column(name="train_no_list")
    private List<Integer> trainNoList = new ArrayList<Integer>();


}
