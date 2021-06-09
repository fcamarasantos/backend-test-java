package br.com.brunobrolesi.parking.resources.dto;

import br.com.brunobrolesi.parking.domain.Vehicle;

import java.util.List;
import java.util.stream.Collectors;

public class VehicleDto {

    private Integer id;
    private String manufacturer;
    private String model;
    private String year;
    private String color;
    private String licensePlate;
    private Integer type;

    public VehicleDto(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.manufacturer = vehicle.getManufacturer();
        this.model = vehicle.getModel();
        this.year = vehicle.getYear();
        this.color = vehicle.getColor();
        this.licensePlate = vehicle.getLicensePlate();
        this.type = vehicle.getType().getId();
    }

    public Integer getId() {
        return id;
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

    public Integer getType() {
        return type;
    }

    public static List<VehicleDto> converter(List<Vehicle> vehicles) {
        return vehicles.stream().map(VehicleDto::new).collect(Collectors.toList());
    }
}
