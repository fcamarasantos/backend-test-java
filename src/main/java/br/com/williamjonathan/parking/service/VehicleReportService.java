package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.Parking;
import br.com.williamjonathan.parking.model.Vehicle;
import br.com.williamjonathan.parking.model.VehicleReport;
import br.com.williamjonathan.parking.model.dto.VehicleReportDto;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface VehicleReportService {

    void entry(Vehicle vehicle, Parking parking);

    void exit(Vehicle vehicle);

    ResponseEntity<?> readAllReportsByParking();

    ResponseEntity<?> readAllReportsByVehicleLicensePlate(String vehicleLicensePlate);

    ResponseEntity<?> readAllReportsByParkingAndEntryDate(String date);

    ResponseEntity<?> readAllReportsByParkingAndEntryDate(String date, String hour);

    List<VehicleReportDto> convertReports(List<VehicleReport> vehicleReportList);
}
