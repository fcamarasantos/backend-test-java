package br.com.williamjonathan.parking.controller;

import br.com.williamjonathan.parking.service.VehicleReportService;
import br.com.williamjonathan.parking.service.VehicleReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private VehicleReportServiceImpl vehicleReportService;

    @GetMapping("/parking")
    public ResponseEntity<?> readAllReportsByParkingAndByEntryDate(@RequestParam(required = false) String date, @RequestParam(required = false) String hour) {
        if(date != null && hour == null) {
            return vehicleReportService.readAllReportsByParkingAndEntryDate(date);
        }
        if(date != null) {
            return vehicleReportService.readAllReportsByParkingAndEntryDate(date, hour);
        }
        return vehicleReportService.readAllReportsByParking();
    }


    @GetMapping("/vehicle/{vehicleLicensePlate}")
    public ResponseEntity<?> readAllReportsByVehicleLicensePlate(@PathVariable String vehicleLicensePlate) {
        return vehicleReportService.readAllReportsByVehicleLicensePlate(vehicleLicensePlate);
    }

}
