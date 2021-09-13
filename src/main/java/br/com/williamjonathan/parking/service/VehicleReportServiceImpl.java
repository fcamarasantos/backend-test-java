package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.Employee;
import br.com.williamjonathan.parking.model.Parking;
import br.com.williamjonathan.parking.model.Vehicle;
import br.com.williamjonathan.parking.model.VehicleReport;
import br.com.williamjonathan.parking.model.dto.VehicleReportDto;
import br.com.williamjonathan.parking.repository.VehicleReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@Service
public class VehicleReportServiceImpl implements VehicleReportService{

    @Autowired
    VehicleReportRepository reportRepository;

    @Autowired
    EmployeeServiceImpl employeeService;

    @Override
    public void entry(Vehicle vehicle, Parking parking) {
        VehicleReport vehicleReport = new VehicleReport(vehicle, parking);
        vehicleReport.setEntryDate(LocalDateTime.now());
        reportRepository.save(vehicleReport);
    }

    @Override
    public void exit(Vehicle vehicle) {
        Optional<VehicleReport> optionalVehicleReport = vehicle.getVehicleReports()
                .stream()
                .filter(vehicleReport -> vehicleReport.getExitDate() == null)
                .findFirst();
        if(optionalVehicleReport.isPresent()) {
            VehicleReport vehicleReport = optionalVehicleReport.get();
            vehicleReport.setExitDate(LocalDateTime.now());

            long minutesParked = ChronoUnit.MINUTES.between( vehicleReport.getEntryDate(), vehicleReport.getExitDate());
            vehicleReport.setTimeParkedInMinutes(minutesParked);

            reportRepository.save(vehicleReport);
        }
    }

    @Override
    public ResponseEntity<?> readAllReportsByParking() {

        List<VehicleReport> allVehicleReports = reportRepository.findAllByParkingId(employeeService.getParkingIdByEmployeeLogged());

        if(!allVehicleReports.isEmpty()) {
            List<VehicleReportDto> vehicleReportDtos = this.convertReports(allVehicleReports);
            return new ResponseEntity<List<VehicleReportDto>>(vehicleReportDtos, HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> readAllReportsByVehicleLicensePlate(String vehicleLicensePlate) {
        List<VehicleReport> allVehicleReports = reportRepository.findAllByVehicleLicensePlate(vehicleLicensePlate);

        if(!allVehicleReports.isEmpty()) {
            List<VehicleReportDto> vehicleReportDtos = this.convertReports(allVehicleReports);
            return new ResponseEntity<List<VehicleReportDto>>(vehicleReportDtos, HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> readAllReportsByParkingAndEntryDate(String date) {
        String dateFormat = date + " 00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateFormat, formatter);

        List<VehicleReport> allVehicleReports = reportRepository.findAllByParkingIdAndEntryDateBetween(employeeService.getParkingIdByEmployeeLogged(), dateTime, dateTime.plusDays(1));

        if(!allVehicleReports.isEmpty()) {
            List<VehicleReportDto> vehicleReportDtos = this.convertReports(allVehicleReports);
            return new ResponseEntity<List<VehicleReportDto>>(vehicleReportDtos, HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> readAllReportsByParkingAndEntryDate(String date, String hour) {
        String dateFormat = date + " " + hour;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateFormat, formatter);

        List<VehicleReport> allVehicleReports = reportRepository.findAllByParkingIdAndEntryDateBetween(employeeService.getParkingIdByEmployeeLogged(), dateTime, dateTime.plusHours(1));

        if(!allVehicleReports.isEmpty()) {
            List<VehicleReportDto> vehicleReportDtos = this.convertReports(allVehicleReports);
            return new ResponseEntity<List<VehicleReportDto>>(vehicleReportDtos, HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public List<VehicleReportDto> convertReports(List<VehicleReport> vehicleReportList) {
        List<VehicleReportDto> vehicleReportDtos = new ArrayList<>();

        vehicleReportList.forEach(
                vehicleReport -> {
                    VehicleReportDto vehicleReportDto = new VehicleReportDto(vehicleReport);
                    vehicleReportDtos.add(vehicleReportDto);
                }
        );
        return vehicleReportDtos;
    }


}
