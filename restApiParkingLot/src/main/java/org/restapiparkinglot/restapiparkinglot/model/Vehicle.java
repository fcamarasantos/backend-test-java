package org.restapiparkinglot.restapiparkinglot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter private String brand;
    @Setter private String model;
    @Setter private String color;
    @Setter private LicensePlate licensePlate;
    @Setter private VehicleType vehicleType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parkinglot_id")
    @JsonIgnore
    @Setter private ParkingLot parkingLot;


    public Vehicle() {

    }
}


