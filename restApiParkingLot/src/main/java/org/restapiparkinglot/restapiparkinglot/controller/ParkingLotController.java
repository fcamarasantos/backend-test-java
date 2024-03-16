package org.restapiparkinglot.restapiparkinglot.controller;

import org.restapiparkinglot.restapiparkinglot.dtos.ParkingLotDTO;
import org.restapiparkinglot.restapiparkinglot.exception.NotFoundException;
import org.restapiparkinglot.restapiparkinglot.model.ParkingLot;
import org.restapiparkinglot.restapiparkinglot.services.ParkingLotService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping(path = "/parkingLot")

public class ParkingLotController {
    @Autowired
    private ParkingLotService parkingLotService;

    @GetMapping
    public ResponseEntity<List<ParkingLot>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(parkingLotService.findAll());
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity<Object> findParkingLotById(@PathVariable(value = "id") int id) throws NotFoundException{
        return ResponseEntity.status(HttpStatus.OK).body(parkingLotService.retrieveById(id));
    }

    @GetMapping (value = "/{cnpj}")
    public ResponseEntity<Object> findParkingLotByCnpj(@PathVariable(value = "cnpj") String cnpj) throws NotFoundException{
        return ResponseEntity.status(HttpStatus.OK).body(parkingLotService.retrieveByCnpj(cnpj));
    }

    @PostMapping
    public ResponseEntity<Object> createParkingLot(@RequestBody @Valid ParkingLotDTO parkingLotDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingLotService.create(parkingLotDTO));
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<Object> updateParkingLot(@RequestBody @Valid ParkingLotDTO parkingLotDTO, @PathVariable(value = "id") int id) throws NotFoundException{
        ParkingLot parkingLot = parkingLotService.retrieveById(id);
        
        BeanUtils.copyProperties(parkingLotDTO, parkingLot);
        return ResponseEntity.status(HttpStatus.OK).body(parkingLotService.update(parkingLot, id));
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity<String> deleteParkingLot(@PathVariable(value = "id") int id) throws NotFoundException{
        return ResponseEntity.status(HttpStatus.OK).body(parkingLotService.delete(id));

    }

}