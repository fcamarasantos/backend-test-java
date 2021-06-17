package br.com.brunobrolesi.parking.resources.dto;

import br.com.brunobrolesi.parking.domain.Address;
import br.com.brunobrolesi.parking.domain.Parking;
import br.com.brunobrolesi.parking.domain.ParkingSpace;
import br.com.brunobrolesi.parking.domain.VehicleType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ParkingResumedDto {
    private Integer id;
    private String cnpj;
    private String name;
    private Integer carSpaces;
    private Integer motorcycleSpaces;

    public ParkingResumedDto (Parking parking) {
        this.id = parking.getId();
        this.cnpj = parking.getCnpj();
        this.name = parking.getName();

        this.carSpaces =  parking.getVehicleSpaceQuantity(VehicleType.CARRO);
        this.motorcycleSpaces = parking.getVehicleSpaceQuantity(VehicleType.MOTO);
    }

    public Integer getCarSpaces() {
        return carSpaces;
    }

    public void setCarSpaces(Integer carSpaces) {
        this.carSpaces = carSpaces;
    }

    public Integer getMotorcycleSpaces() {
        return motorcycleSpaces;
    }

    public void setMotorcycleSpaces(Integer motorcycleSpaces) {
        this.motorcycleSpaces = motorcycleSpaces;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<ParkingResumedDto> converter(List<Parking> parkings) {
        return parkings.stream().map(ParkingResumedDto::new).collect(Collectors.toList());
    }
}
