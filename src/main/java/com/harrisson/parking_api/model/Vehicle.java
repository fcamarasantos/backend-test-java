package com.harrisson.parking_api.model;

import com.harrisson.parking_api.enums.Type;
import com.harrisson.parking_api.to.VehicleData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vehicle", schema = "parking")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String brand;
    private String model;
    private String color;
    private String plate;
    @Enumerated(EnumType.STRING)
    private Type type;

    public Vehicle(VehicleData vehicleData) {
        this.brand = vehicleData.brand();
        this.model = vehicleData.model();
        this.color = vehicleData.color();
        this.plate = vehicleData.plate();
        this.type = vehicleData.type();
    }

    public void updateVehicle(Vehicle vehicle) {
        if (vehicle.brand != null) this.brand = vehicle.brand;
        if (vehicle.model != null) this.model = vehicle.model;
        if (vehicle.color != null) this.color = vehicle.color;
        if (vehicle.plate != null) this.plate = vehicle.plate;
        if (vehicle.type != null) this.type = vehicle.type;
    }
}

