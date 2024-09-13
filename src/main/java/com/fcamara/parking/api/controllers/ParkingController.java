package com.fcamara.parking.api.controllers;

import com.fcamara.parking.api.domain.entities.Parking;
import com.fcamara.parking.api.services.ParkingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingService parkingService;

    @PostMapping("/entry")
    public ResponseEntity<Parking> registerEntry(@RequestParam Long vehicleId, @RequestParam Long establishmentId) {
        Parking parking = parkingService.registerEntry(vehicleId, establishmentId);
        return ResponseEntity.ok(parking);
    }

    @PostMapping("/exit/{parkingId}")
    public ResponseEntity<Parking> registerExit(@PathVariable Long parkingId) {
        Parking parking = parkingService.registerExit(parkingId);
        return ResponseEntity.ok(parking);
    }

    @GetMapping
    public ResponseEntity<List<Parking>> getAllParkings() {
        List<Parking> parkings = parkingService.getAll();
        return ResponseEntity.ok(parkings);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<Parking>> getParkingsByVehicle(@PathVariable Long vehicleId) {
        List<Parking> parkings = parkingService.getParkingsByVehicle(vehicleId);
        return ResponseEntity.ok(parkings);
    }

    @GetMapping("/establishment/{establishmentId}")
    public ResponseEntity<List<Parking>> getParkingsByEstablishment(@PathVariable Long establishmentId) {
        List<Parking> parkings = parkingService.getParkingsByEstablishment(establishmentId);
        return ResponseEntity.ok(parkings);
    }

}
