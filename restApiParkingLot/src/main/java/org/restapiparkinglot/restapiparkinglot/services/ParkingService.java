package org.restapiparkinglot.restapiparkinglot.services;

import org.restapiparkinglot.restapiparkinglot.exception.NotFoundException;
import org.restapiparkinglot.restapiparkinglot.exception.ParkingLotFullException;
import org.restapiparkinglot.restapiparkinglot.model.ParkingLot;
import org.restapiparkinglot.restapiparkinglot.model.Vehicle;
import org.restapiparkinglot.restapiparkinglot.repository.ParkingLotRepository;
import org.restapiparkinglot.restapiparkinglot.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingService {
    @Autowired
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;


    public String entry(int parkingLotId, int vehicleId) throws NotFoundException{
        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotId)
        .orElseThrow(() -> new NotFoundException("Parking lot not found"));
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
        .orElseThrow(() -> new NotFoundException("Vehicle not found"));

        switch(vehicle.getVehicleType()){
            case CAR:
                if (parkingLot.getCarSpaces()==parkingLot.getTotalParkedCar()) throw new ParkingLotFullException("Parking lot is full");
                parkingLot.setTotalParkedCar(parkingLot.getTotalParkedCar()+1);
            
            case MOTORCYCLE:
                if (parkingLot.getMotorcycleSpaces()==parkingLot.getTotalParkedMotorcycle()) throw new ParkingLotFullException("Parking lot is full");
                parkingLot.setTotalParkedMotorcycle(parkingLot.getTotalParkedMotorcycle()+1);
        }
        parkingLot.getVehicles().add(vehicle);
        vehicle.setParkingLot(parkingLot);

        vehicleRepository.save(vehicle);
        parkingLotRepository.save(parkingLot);

        return "Entry successfully";

    }

    public String exit(int parkingLotId, int vehicleId){
        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotId)
        .orElseThrow(() -> new NotFoundException("Parking lot not found"));
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
        .orElseThrow(() -> new NotFoundException("Vehicle not found"));

        switch(vehicle.getVehicleType()){
            case CAR:
                parkingLot.setTotalParkedCar(parkingLot.getTotalParkedCar()-1);
            
            case MOTORCYCLE:
                parkingLot.setTotalParkedMotorcycle(parkingLot.getTotalParkedMotorcycle()-1);
        }
        parkingLot.getVehicles().remove(vehicle);
        vehicle.setParkingLot(null);

        vehicleRepository.save(vehicle);
        parkingLotRepository.save(parkingLot);

        return "Exit successfully";
    }
}
