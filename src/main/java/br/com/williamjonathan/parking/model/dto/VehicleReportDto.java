package br.com.williamjonathan.parking.model.dto;

import br.com.williamjonathan.parking.model.Vehicle;
import br.com.williamjonathan.parking.model.VehicleReport;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VehicleReportDto {

    private Long id;

    private LocalDateTime entryDate;

    private LocalDateTime exitDate;

    private long timeParkedinMinutes;

    private String vehicleLicensePlate;

    public VehicleReportDto() {
    }

    public VehicleReportDto(VehicleReport vehicleReport) {
        this.id = vehicleReport.getId();
        this.entryDate = vehicleReport.getEntryDate();
        this.exitDate = vehicleReport.getExitDate();
        this.timeParkedinMinutes = vehicleReport.getTimeParkedInMinutes();
        this.vehicleLicensePlate = vehicleReport.getVehicle().getLicensePlate();
    }


    public static List<VehicleReportDto> convert(List<VehicleReport> vehicleReports) {
        List<VehicleReportDto> vehicleReportDtos = new ArrayList<>();
        vehicleReports.forEach(
                vehicleReport -> {
                    VehicleReportDto v = new VehicleReportDto(vehicleReport);
                    vehicleReportDtos.add(v);
                }
        );
        return vehicleReportDtos;
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

    public long getTimeParkedinMinutes() {
        return timeParkedinMinutes;
    }

    public void setTimeParkedinMinutes(long timeParkedinMinutes) {
        this.timeParkedinMinutes = timeParkedinMinutes;
    }

    public String getVehicleLicensePlate() {
        return vehicleLicensePlate;
    }

    public void setVehicleLicensePlate(String vehicleLicensePlate) {
        this.vehicleLicensePlate = vehicleLicensePlate;
    }
}
