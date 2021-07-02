package br.com.brunobrolesi.parking.controller.form;

import br.com.brunobrolesi.parking.model.Vehicle;
import br.com.brunobrolesi.parking.model.VehicleType;
import br.com.brunobrolesi.parking.repositories.VehicleRepository;
import br.com.brunobrolesi.parking.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateVehicleForm {

    @Autowired
    VehicleService service;

    @NotNull @NotEmpty
    private String manufacturer;
    @NotNull @NotEmpty
    private String model;
    @NotNull @NotEmpty
    private String year;
    @NotNull @NotEmpty
    private String color;
    @NotNull @NotEmpty
    private String licensePlate;
    @NotNull
    private Integer type;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Vehicle update(Integer id) {
        Vehicle vehicle = service.findById(id);

        vehicle.setManufacturer(this.manufacturer);
        vehicle.setModel(this.model);
        vehicle.setYear(this.year);
        vehicle.setColor(this.color);
        vehicle.setLicensePlate(this.licensePlate);
        vehicle.setType(VehicleType.toEnum(this.type));

        return vehicle;
    }
}
