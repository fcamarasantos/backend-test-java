package org.restapiparkinglot.restapiparkinglot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;


@Builder
@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "brand", nullable = false)
    private String brand;
    @Column(name = "model", nullable = false)
    private String model;
    @Column(name = "color", nullable = false)
    private String color;
    @Column(name = "licensePlate", nullable = false, unique = true)
    private String licensePlate;
    @Column(name = "vehicleType", nullable = false)
    private VehicleType vehicleType;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parkinglot_id")
    @JsonIgnore
    private ParkingLot parkingLot;


    public Vehicle() {

    }

    public Vehicle(int id, String brand, String model, String color, String licensePlate, VehicleType vehicleType, ParkingLot parkingLot) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.parkingLot = parkingLot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}


