package org.restapiparkinglot.restapiparkinglot.controller;

import jakarta.validation.Valid;
import org.restapiparkinglot.restapiparkinglot.dtos.VehicleDTO;
import org.restapiparkinglot.restapiparkinglot.exception.NotFoundException;
import org.restapiparkinglot.restapiparkinglot.model.Vehicle;
import org.restapiparkinglot.restapiparkinglot.services.VehicleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<Vehicle>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.findAll());
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity<Object> findVehicleById(@PathVariable(value = "id") int id) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.retrieveById(id));
    }

    @GetMapping (value = "/{licensePlate}")
    public ResponseEntity<Object> findVehicleByLicensePlate(@PathVariable(value = "licensePlate") String licensePlate) throws NotFoundException{
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.retrieveByLicensePlate(licensePlate));
    }

    @PostMapping
    public ResponseEntity<Object> createVehicle(@RequestBody @Valid VehicleDTO vehicleDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.create(vehicleDTO));
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<Object> updateVehicle(@RequestBody @Valid VehicleDTO vehicleDTO, @PathVariable(value = "id") int id) throws NotFoundException{
        Vehicle vehicle = vehicleService.retrieveById(id);
        
        BeanUtils.copyProperties(vehicleDTO,vehicle);
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.update(vehicle, id));
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable(value = "id") int id) throws NotFoundException{
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.delete(id));

    }

}
