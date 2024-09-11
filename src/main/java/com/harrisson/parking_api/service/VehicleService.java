package com.harrisson.parking_api.service;

import com.harrisson.parking_api.model.Vehicle;
import com.harrisson.parking_api.repository.VehicleRepository;
import com.harrisson.parking_api.to.VehicleDataDetails;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;


    public void save(Vehicle vehicle) {
        try {
            repository.save(vehicle);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar veículo" + e.getCause());
        }
    }

    public Page<VehicleDataDetails> getVehicles(Pageable page) {
        return repository.findAll(page).map(VehicleDataDetails::new);
    }

    public Vehicle getById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void deleteVehicle(Long id) {
        repository.deleteById(id);
    }

    public Vehicle getByPlate(String plate) {
        return repository.findByPlate(plate);
    }
}
