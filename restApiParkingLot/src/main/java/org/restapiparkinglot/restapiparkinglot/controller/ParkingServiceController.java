package org.restapiparkinglot.restapiparkinglot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.restapiparkinglot.restapiparkinglot.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping ("/parkingService" )
@Api("Rest Api Parking Lot")
public class ParkingServiceController {
    @Autowired
    private ParkingService parkingService;

    @PostMapping("/{parkingLot}/entry/{vehicle}")
    @ApiOperation("Route for entry of vehicle")
    public ResponseEntity<String> entryVehicle(@PathVariable("parkingLot") int parkingLotId, @PathVariable("vehicle") int vehicleId){
        return ResponseEntity.status(HttpStatus.OK).body(parkingService.entry(parkingLotId, vehicleId));
    }

    @PostMapping("/{parkingLot}/exit/{vehicle}")
    @ApiOperation("Route for exit of vehicle")
    public ResponseEntity<String> exitVehicle(@PathVariable("parkingLot") int parkingLotId, @PathVariable("vehicle") int vehicleId){
        return ResponseEntity.status(HttpStatus.OK).body(parkingService.exit(parkingLotId, vehicleId));
    }



}
