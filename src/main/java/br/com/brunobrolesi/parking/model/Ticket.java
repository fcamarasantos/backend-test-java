package br.com.brunobrolesi.parking.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name="parkingSpace_id")
    private ParkingSpace parkingSpace;

    private LocalDate entryTime;

    private LocalDate exitTime;

    public Ticket() {}

    public Ticket(Integer id, Vehicle vehicle, ParkingSpace parkingSpace) {
        this.id = id;
        this.vehicle = vehicle;
        this.parkingSpace = parkingSpace;
        this.entryTime = LocalDate.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public LocalDate getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDate entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDate getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDate exitTime) {
        this.exitTime = exitTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id.equals(ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
