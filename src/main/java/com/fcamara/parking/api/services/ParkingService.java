package com.fcamara.parking.api.services;

import com.fcamara.parking.api.domain.entities.Parking;
import com.fcamara.parking.api.domain.entities.Vehicle;
import com.fcamara.parking.api.domain.entities.Establishment;
import com.fcamara.parking.api.exceptions.ResourceNotFoundException;
import com.fcamara.parking.api.repositories.ParkingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;
    private final VehicleService vehicleService;
    private final EstablishmentService establishmentService;

    public Parking registerEntry(Long vehicleId, Long establishmentId) {
        Vehicle vehicle = vehicleService.getById(vehicleId);
        Establishment establishment = establishmentService.getById(establishmentId);

        Parking parking = Parking.builder()
                .vehicle(vehicle)
                .establishment(establishment)
                .entryTime(LocalDateTime.now())
                .build();
        return parkingRepository.save(parking);
    }

    public Parking registerExit(Long parkingId) {
        Optional<Parking> parkingOpt = parkingRepository.findById(parkingId);

        if (parkingOpt.isPresent()) {
            Parking parking = parkingOpt.get();
            parking.setExitTime(LocalDateTime.now());

            return parkingRepository.save(parking);
        } else {
            throw new ResourceNotFoundException("Controle de entrarada com id " + parkingId + " não encontrado");
        }
    }

    public List<Parking> getAll() {
        return parkingRepository.findAll();
    }

    public List<Parking> getParkingsByVehicle(Long vehicleId) {
        return parkingRepository.findByVehicleId(vehicleId);
    }

    public List<Parking> getParkingsByEstablishment(Long establishmentId) {
        return parkingRepository.findByEstablishmentId(establishmentId);
    }

}
