package com.smartmetro.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(RouteId.class)
@Table(name = "route_station")
public class RouteStation {
    @Id
    @Column(name = "route_id")
    private int routeId;

    @Id
    @Column(name = "station_id")
    private int stationId;

    @Column(name = "sequence_number")
    private int sequenceNumber;

}
