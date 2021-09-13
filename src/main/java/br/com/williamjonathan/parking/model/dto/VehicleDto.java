package br.com.williamjonathan.parking.model.dto;

import br.com.williamjonathan.parking.model.Vehicle;

import java.util.List;

public class VehicleDto {

    private String licensePlate;
    private String color;
    private String vehicleBrand;
    private String vehicleModel;
    private String isParked;

    public VehicleDto(Vehicle vehicle) {
        this.licensePlate = vehicle.getLicensePlate();
        this.color = vehicle.getColor();
        this.vehicleBrand = vehicle.getVehicleModel().getVehicleBrand().getName();
        this.vehicleModel = vehicle.getVehicleModel().getName();
        this.isParked = isParked(vehicle);
    }

    public String isParked(Vehicle vehicle) {
        if(vehicle.getParking() == null) {
            return "NO";
        }
        return "YES. Parking ID: " + vehicle.getParking().getId();
    }


    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getIsParked() {
        return isParked;
    }

    public void setIsParked(String isParked) {
        this.isParked = isParked;
    }
}
