package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.VehicleModel;
import br.com.williamjonathan.parking.repository.VehicleModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleModelServiceImpl implements VehicleModelService{

    @Autowired
    VehicleModelRepository vehicleModelRepository;

    @Override
    public Optional<VehicleModel> searchByName(String name) {
        Optional<VehicleModel> optionalVehicleModel = vehicleModelRepository.findByName(name);
        return optionalVehicleModel;
    }
}
