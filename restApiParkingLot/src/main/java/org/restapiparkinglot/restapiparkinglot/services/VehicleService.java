package org.restapiparkinglot.restapiparkinglot.services;

import org.restapiparkinglot.restapiparkinglot.dtos.VehicleDTO;
import org.restapiparkinglot.restapiparkinglot.exception.AlreadyRegisteredException;
import org.restapiparkinglot.restapiparkinglot.exception.NotFoundException;
import org.restapiparkinglot.restapiparkinglot.model.Vehicle;
import org.restapiparkinglot.restapiparkinglot.repository.VehicleRepository;
import org.springframework.beans.BeanUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle create(VehicleDTO vehicleDTO){
        isRegistered(vehicleDTO.getLicensePlate());
        Vehicle vehicleModel = convertVehicle(vehicleDTO);
        return vehicleRepository.save(vehicleModel);
    }

    public Vehicle retrieveById(int id) throws NotFoundException{
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vehicle not found"));
    }

    public Vehicle retrieveByLicensePlate(String licensePlate) throws NotFoundException{
        return vehicleRepository.findByLicensePlate(licensePlate).
                orElseThrow(() -> new NotFoundException("Vehicle not found"));
    }

    public Vehicle update(Vehicle vehicle, int id) throws NotFoundException {
        retrieveById(id);
        return vehicleRepository.save(vehicle);
    }

    public String delete(int id) throws NotFoundException {
        retrieveById(id);
        vehicleRepository.deleteById(id);
        return "Vehicle successfully deleted";
    }

    private Vehicle convertVehicle(VehicleDTO vehicleDTO){
        Vehicle vehicleModel = new Vehicle();
        BeanUtils.copyProperties(vehicleDTO, vehicleModel);
        return vehicleModel;
    }

    private void isRegistered(String licensePlate) throws AlreadyRegisteredException {
        vehicleRepository.findByLicensePlate(licensePlate)
        .orElseThrow(() -> new AlreadyRegisteredException("Vehicle already registered"));
    }
}
