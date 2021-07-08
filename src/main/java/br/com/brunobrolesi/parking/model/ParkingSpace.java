package br.com.brunobrolesi.parking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ParkingSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer vehicleType;
    private Integer state;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "parking_id")
    private Parking parking;

    public ParkingSpace() {}

    public ParkingSpace(Integer id, VehicleType vehicleType, Parking parking) {
        this.id = id;
        this.vehicleType = vehicleType.getId();
        this.state = ParkingSpaceState.FREE.getId();
        this.parking = parking;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public VehicleType getVehicleType() {
        return VehicleType.toEnum(vehicleType);
    }

    public void setType(VehicleType vehicleType) {
        this.vehicleType = vehicleType.getId();
    }

    public ParkingSpaceState getState() {
        return ParkingSpaceState.toEnum(this.state);
    }

    public void setState(ParkingSpaceState state) {
        this.state = state.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSpace that = (ParkingSpace) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
