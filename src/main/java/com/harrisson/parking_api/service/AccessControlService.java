package com.harrisson.parking_api.service;

import com.harrisson.parking_api.enums.Type;
import com.harrisson.parking_api.model.AccessControl;
import com.harrisson.parking_api.model.Establishment;
import com.harrisson.parking_api.model.Vehicle;
import com.harrisson.parking_api.repository.AccessControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccessControlService {

    @Autowired
    private AccessControlRepository accessControlRepository;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private EstablishmentService establishmentService;

    public AccessControl registerEntry(Long vehicleId, Long establishmentId) {
        Vehicle vehicle = vehicleService.getById(vehicleId);
        Establishment establishment = establishmentService.getById(establishmentId);

        if (vehicle.getType().equals(Type.CAR)) {
            int currentCarCount = accessControlRepository.countByEstablishmentIdAndVehicleType(establishmentId, "CAR");
            if (currentCarCount >= establishment.getCarQuantity()) {
                throw new RuntimeException("Limite de vagas para carros atingido");
            }
        } else if (vehicle.getType().equals(Type.MOTORCYCLE)) {
            int currentMotorcycleCount = accessControlRepository.countByEstablishmentIdAndVehicleType(establishmentId, "MOTORCYCLE");
            if (currentMotorcycleCount >= establishment.getMotorcycleQuantity()) {
                throw new RuntimeException("Limite de vagas para motocicletas atingido");
            }
        }

        AccessControl accessControl = AccessControl.builder()
                .vehicle(vehicle)
                .establishment(establishment)
                .entryTime(LocalDateTime.now())
                .build();

        return accessControlRepository.save(accessControl);
    }

    public AccessControl registerExit(Long vehicleId, Long establishmentId) {
        AccessControl accessControl = accessControlRepository.findByVehicleIdAndEstablishmentIdAndExitTimeIsNull(vehicleId, establishmentId);
        if (accessControl == null) {
            throw new RuntimeException("Entrada não encontrada para o veículo e estabelecimento especificados");
        }
        accessControl.setExitTime(LocalDateTime.now());
        return accessControlRepository.save(accessControl);
    }
}