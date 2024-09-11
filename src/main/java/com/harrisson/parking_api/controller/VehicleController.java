package com.harrisson.parking_api.controller;

import com.harrisson.parking_api.model.Vehicle;
import com.harrisson.parking_api.service.VehicleService;
import com.harrisson.parking_api.to.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService service;

    @PostMapping
    public ResponseEntity<VehicleDataDetails> createVehicle(@RequestBody @Valid VehicleData vehicleData, UriComponentsBuilder uriBuilder) {
        var vehicle = new Vehicle(vehicleData);
        service.save(vehicle);
        var uri = uriBuilder.path("/vehicles/getById/{id}").buildAndExpand(vehicle.getId()).toUri();
        return ResponseEntity.created(uri).body(new VehicleDataDetails(vehicle));
    }

    @GetMapping("/{size}")
    public ResponseEntity<Page<VehicleDataDetails>> getVehicles(@PageableDefault(size = 10) Pageable page, @PathVariable Integer size) {
        Pageable pageable = PageRequest.of(page.getPageNumber(), size);
        return ResponseEntity.ok(service.getVehicles(pageable));
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<VehicleData> getVehicleById(@PathVariable Long id) {
        var vehicle = service.getById(id);
        return ResponseEntity.ok(new VehicleData(vehicle));
    }
    @PutMapping
    public ResponseEntity<VehicleDataDetails> updateEstablishment(@RequestBody VehicleDataDetails vehicleDataDetails) {
        var vehicle = service.getById(vehicleDataDetails.id());
        vehicle.updateVehicle(vehicle);
        return ResponseEntity.ok(new VehicleDataDetails(vehicle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        service.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Vehicle> getVehicleByPlate(@RequestParam String plate) {
        Vehicle vehicle = service.getByPlate(plate);
        return ResponseEntity.ok(vehicle);
    }

}
