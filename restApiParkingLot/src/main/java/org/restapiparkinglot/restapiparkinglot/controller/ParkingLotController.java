package org.restapiparkinglot.restapiparkinglot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.restapiparkinglot.restapiparkinglot.dtos.ParkingLotDTO;
import org.restapiparkinglot.restapiparkinglot.exception.NotFoundException;
import org.restapiparkinglot.restapiparkinglot.model.ParkingLot;
import org.restapiparkinglot.restapiparkinglot.services.ParkingLotService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/parkingLot")
@Api("Rest Api Parking Lot")
public class ParkingLotController {
    @Autowired
    private ParkingLotService parkingLotService;

    @GetMapping
    @ApiOperation("Route for listing all parking lots")
    public ResponseEntity<List<ParkingLot>> findAllParkingLot(){
        return ResponseEntity.status(HttpStatus.OK).body(parkingLotService.findAll());
    }

    @GetMapping ("/{id}")
    @ApiOperation("Route for searching a parking lot by its id")
    public ResponseEntity<Object> findParkingLotById(@PathVariable("id") int id) throws NotFoundException{
        return ResponseEntity.status(HttpStatus.OK).body(parkingLotService.retrieveById(id));
    }

    @GetMapping ("/{cnpj}")
    @ApiOperation("Route for searching a parking lot by its cnpj")
    public ResponseEntity<Object> findParkingLotByCnpj(@PathVariable("cnpj") String cnpj) throws NotFoundException{
        return ResponseEntity.status(HttpStatus.OK).body(parkingLotService.retrieveByCnpj(cnpj));
    }

    @PostMapping
    @ApiOperation("Route for creating a parking lot")
    public ResponseEntity<Object> createParkingLot(@RequestBody @Valid ParkingLotDTO parkingLotDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingLotService.create(parkingLotDTO));
    }

    @PutMapping ("/{id}")
    @ApiOperation("Route for updating a parking lot")
    public ResponseEntity<Object> updateParkingLot(@RequestBody @Valid ParkingLotDTO parkingLotDTO, @PathVariable("id") int id) throws NotFoundException{
        ParkingLot parkingLot = parkingLotService.retrieveById(id);
        
        BeanUtils.copyProperties(parkingLotDTO, parkingLot);
        return ResponseEntity.status(HttpStatus.OK).body(parkingLotService.update(parkingLot, id));
    }

    @DeleteMapping ("/{id}")
    @ApiOperation("Route for deleting a parking lot")
    public ResponseEntity<String> deleteParkingLot(@PathVariable("id") int id) throws NotFoundException{
        return ResponseEntity.status(HttpStatus.OK).body(parkingLotService.delete(id));

    }

}