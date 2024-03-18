package org.restapiparkinglot.restapiparkinglot.model;

import jakarta.persistence.*;

import lombok.Builder;

import java.util.List;

@Builder
@Entity
@Table(name = "parkinglot")
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "cnpj", nullable = false, unique = true, length = 14)
    private String cnpj;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "phonenumber", nullable = false, unique = true, length = 11)
    private String phoneNumber;
    @Column(name = "motorcycleSpaces", nullable = false)
    private int motorcycleSpaces;
    @Column(name = "carSpaces", nullable = false)
    private int carSpaces;
    @Column(name = "totalparkedmotorcycle")
    private int totalParkedMotorcycle;
    @Column(name = "totalparkedcar")
    private int totalParkedCar;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "vehicles",
            joinColumns = @JoinColumn(name = "parkinglot_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id", referencedColumnName = "id"))

    private List<Vehicle> vehicles;

    public ParkingLot() {

    }

    public ParkingLot(int id, String name, String cnpj, String address, String phoneNumber, int motorcycleSpaces, int carSpaces, int totalParkedMotorcycle, int totalParkedCar, List<Vehicle> vehicles) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.motorcycleSpaces = motorcycleSpaces;
        this.carSpaces = carSpaces;
        this.totalParkedMotorcycle = totalParkedMotorcycle;
        this.totalParkedCar = totalParkedCar;
        this.vehicles = vehicles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getMotorcycleSpaces() {
        return motorcycleSpaces;
    }

    public void setMotorcycleSpaces(int motorcycleSpaces) {
        this.motorcycleSpaces = motorcycleSpaces;
    }

    public int getCarSpaces() {
        return carSpaces;
    }

    public void setCarSpaces(int carSpaces) {
        this.carSpaces = carSpaces;
    }

    public int getTotalParkedMotorcycle() {
        return totalParkedMotorcycle;
    }

    public void setTotalParkedMotorcycle(int totalParkedMotorcycle) {
        this.totalParkedMotorcycle = totalParkedMotorcycle;
    }

    public int getTotalParkedCar() {
        return totalParkedCar;
    }

    public void setTotalParkedCar(int totalParkedCar) {
        this.totalParkedCar = totalParkedCar;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}

