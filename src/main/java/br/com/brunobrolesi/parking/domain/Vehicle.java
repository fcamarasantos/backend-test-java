package br.com.brunobrolesi.parking.domain;

import java.util.Locale;

public class Vehicle {
    String manufacturer;
    String model;
    String year;
    String color;
    String licensePlate;
    String vehicleType;

    public Vehicle() {
    }

    public Vehicle(String manufacturer, String model, String year, String color, String licensePlate, String vehicleType) {
        this.manufacturer = manufacturer.toLowerCase(Locale.ENGLISH);
        this.model = model;
        this.year = year;
        this.color = color;
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer.toLowerCase(Locale.ENGLISH);
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}
