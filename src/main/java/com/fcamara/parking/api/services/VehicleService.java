package com.fcamara.parking.api.services;

import com.fcamara.parking.api.domain.dto.VehiclePostDTO;
import com.fcamara.parking.api.domain.entities.Vehicle;
import com.fcamara.parking.api.exceptions.ResourceNotFoundException;
import com.fcamara.parking.api.mappers.VehicleMapper;
import com.fcamara.parking.api.repositories.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public Vehicle create(VehiclePostDTO vehicle) {
        return vehicleRepository.save(VehicleMapper.toEntity(vehicle));
    }

    public Vehicle getById(Long id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado"));
    }

    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle update(Long id, VehiclePostDTO vehicle) {
        vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado"));
        return vehicleRepository.save(VehicleMapper.toEntity(vehicle));
    }

    public void delete(Long id) {
        vehicleRepository.deleteById(id);
    }
}
