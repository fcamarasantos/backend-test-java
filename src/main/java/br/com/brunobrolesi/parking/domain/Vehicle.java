package br.com.brunobrolesi.parking.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

@Entity
public class Vehicle implements Serializable {
    private static final long SerialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String manufacturer;
    private String model;
    private String year;
    private String color;
    @Column(unique = true)
    private String licensePlate;
    private Integer type;

    public Vehicle() {
    }

    public Vehicle(Integer id, String manufacturer, String model, String year, String color, String licensePlate, VehicleType type) {
        if(!licensePlate.toUpperCase(Locale.ENGLISH).matches("[A-Z]{2,3}[0-9]{4}|[A-Z]{3,4}[0-9]{3}|[A-Z0-9]{7}")) {
            throw new IllegalArgumentException("Placa inválida: " + licensePlate);
        }
        if(!year.matches("[0-9]{4}") || (Integer.parseInt(year) < 1886 || Integer.parseInt(year) > LocalDate.now().getYear() + 1)) {
            throw new IllegalArgumentException("Ano inválido: " + year);
        }
        this.id = id;
        this.manufacturer = manufacturer.toLowerCase(Locale.ENGLISH);
        this.model = model.toLowerCase(Locale.ENGLISH);
        this.year = year;
        this.color = color.toLowerCase(Locale.ENGLISH);
        this.licensePlate = licensePlate.toUpperCase(Locale.ENGLISH);
        this.type = type.getId();
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

    public VehicleType getType() {
        return VehicleType.toEnum(this.type);
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer.toLowerCase(Locale.ENGLISH);
    }

    public void setModel(String model) {
        this.model = model.toLowerCase(Locale.ENGLISH);
    }

    public void setYear(String year) {
        if(!year.matches("[0-9]{4}") || (Integer.parseInt(year) < 1886 || Integer.parseInt(year) > LocalDate.now().getYear() + 1)) {
            throw new IllegalArgumentException("Ano inválido: " + year);
        }
        this.year = year;
    }

    public void setColor(String color) {
        this.color = color.toLowerCase(Locale.ENGLISH);
    }

    public void setLicensePlate(String licensePlate) {
        if(!licensePlate.toUpperCase(Locale.ENGLISH).matches("[A-Z]{2,3}[0-9]{4}|[A-Z]{3,4}[0-9]{3}|[A-Z0-9]{7}")) {
            throw new IllegalArgumentException("Placa inválida: " + licensePlate);
        }
        this.licensePlate = licensePlate.toUpperCase(Locale.ENGLISH);
    }

    public void setType(VehicleType type) {
        this.type = type.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return id.equals(vehicle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
