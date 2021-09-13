package br.com.williamjonathan.parking.controller;

import br.com.williamjonathan.parking.model.form.VehicleForm;
import br.com.williamjonathan.parking.service.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleServiceImpl vehicleService;

    @GetMapping(value = "/{licensePlate}/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> readJson(@PathVariable String licensePlate) {
        return vehicleService.read(licensePlate);
    }

    @GetMapping(value = "/{licensePlate}/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> readXml(@PathVariable String licensePlate) {
        return vehicleService.read(licensePlate);
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> readInXml() {
        return vehicleService.readVehicleLogged();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid VehicleForm form) {
        return vehicleService.create(form);
    }

    @PutMapping("/{licensePlate}/parking/{parkingId}")
    public ResponseEntity<?> park(@PathVariable String licensePlate, @PathVariable Long parkingId) {
        return vehicleService.park(parkingId, licensePlate);
    }

    @DeleteMapping("/{licensePlate}")
    public ResponseEntity<?> unpark(@PathVariable String licensePlate) {
        return vehicleService.unpark(licensePlate);
    }

}
