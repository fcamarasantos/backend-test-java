package br.com.williamjonathan.parking.model;



import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles_reports")
public class VehicleReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime entryDate;

    private LocalDateTime exitDate;

    private Long timeParkedInMinutes;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "parking_id", referencedColumnName = "id")
    private Parking parking;

    public VehicleReport() {
    }

    public VehicleReport(Vehicle vehicle, Parking parking) {
        this.vehicle = vehicle;
        this.parking = parking;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDateTime getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDateTime exitDate) {
        this.exitDate = exitDate;
    }

    public Long getTimeParkedInMinutes() {
        return timeParkedInMinutes;
    }

    public void setTimeParkedInMinutes(Long timeParkedInMinutes) {
        this.timeParkedInMinutes = timeParkedInMinutes;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }
}
