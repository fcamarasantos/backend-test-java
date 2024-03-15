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
    @Setter
    @Column(name = "brand", nullable = false) private String brand;
    @Setter
    @Column(name = "model", nullable = false) private String model;
    @Setter
    @Column(name = "color", nullable = false) private String color;
    @Setter
    @Column(name = "licensePlate", nullable = false, unique = true) private String licensePlate;
    @Setter
    @Column(name = "vehicleType", nullable = false) private VehicleType vehicleType;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parkinglot_id")
    @JsonIgnore
    @Setter private ParkingLot parkingLot;


    public Vehicle() {

    }
}


