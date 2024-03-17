package org.restapiparkinglot.restapiparkinglot.controller;

import org.restapiparkinglot.restapiparkinglot.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping (path = "/parkingService" )
public class ParkingServiceController {
    @Autowired
    private ParkingService parkingService;

    @PostMapping(path = "/{parkingLot}/entry/{vehicle}")
    public ResponseEntity<String> entryVehicle(@PathVariable("parkingLot") int parkingLotId, @PathVariable("vehicle") int vehicleId){
        return ResponseEntity.status(HttpStatus.OK).body(parkingService.entry(parkingLotId, vehicleId));
    }

    @PostMapping(path = "/{parkingLot}/exit/{vehicle}")
    public ResponseEntity<String> exitVehicle(@PathVariable("parkingLot") int parkingLotId, @PathVariable("vehicle") int vehicleId){
        return ResponseEntity.status(HttpStatus.OK).body(parkingService.exit(parkingLotId, vehicleId));
    }



}
