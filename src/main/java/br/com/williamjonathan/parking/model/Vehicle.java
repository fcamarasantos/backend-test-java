package br.com.williamjonathan.parking.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String color;

    @Column(unique = true)
    private String licensePlate;

    @ManyToOne
    private Parking parking;

    @ManyToOne
    private VehicleModel vehicleModel;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicle")
    private List<VehicleReport> vehicleReports;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public List<VehicleReport> getVehicleEntryAndExits() {
        return vehicleReports;
    }

    public void setVehicleEntryAndExits(List<VehicleReport> vehicleReports) {
        this.vehicleReports = vehicleReports;
    }
}
