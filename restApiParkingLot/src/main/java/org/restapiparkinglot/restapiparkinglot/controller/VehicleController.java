package org.restapiparkinglot.restapiparkinglot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/vehicle")
@Api("Rest Api Parking Lot")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    @ApiOperation("Route for listing all vehicles")
    public ResponseEntity<List<Vehicle>> findAllVehicles(){
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.findAll());
    }

    @GetMapping ( "/{id}")
    @ApiOperation("Route for searching a vehicle by its id")
    public ResponseEntity<Object> findVehicleById(@PathVariable("id") int id) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.retrieveById(id));
    }

    @GetMapping ("/{licensePlate}")
    @ApiOperation("Route for searching a vehicle by its license plate")
    public ResponseEntity<Object> findVehicleByLicensePlate(@PathVariable("licensePlate") String licensePlate) throws NotFoundException{
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.retrieveByLicensePlate(licensePlate));
    }

    @PostMapping
    @ApiOperation("Route for creating a vehicle")
    public ResponseEntity<Object> createVehicle(@RequestBody @Valid VehicleDTO vehicleDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.create(vehicleDTO));
    }

    @PutMapping ("/{id}")
    @ApiOperation("Route for updating a vehicle")
    public ResponseEntity<Object> updateVehicle(@RequestBody @Valid VehicleDTO vehicleDTO, @PathVariable("id") int id) throws NotFoundException{
        Vehicle vehicle = vehicleService.retrieveById(id);
        
        BeanUtils.copyProperties(vehicleDTO,vehicle);
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.update(vehicle, id));
    }

    @DeleteMapping ("/{id}")
    @ApiOperation("Route for deleting a vehicle")
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) throws NotFoundException{
        return ResponseEntity.status(HttpStatus.OK).body(vehicleService.delete(id));

    }

}
