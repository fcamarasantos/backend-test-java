package com.fcamara.parking.api.mappers;

import com.fcamara.parking.api.domain.entities.Vehicle;
import com.fcamara.parking.api.domain.dto.VehiclePostDTO;

public class VehicleMapper {
    public static Vehicle toEntity(VehiclePostDTO dto) {
        if (dto == null) {
            return null;
        }

        return Vehicle.builder()
                .brand(dto.getBrand())
                .model(dto.getModel())
                .color(dto.getColor())
                .licensePlate(dto.getLicensePlate())
                .type(dto.getType())
                .build();
    }

    public static VehiclePostDTO toDTO(Vehicle vehicle) {
        if (vehicle == null) {
            return null;
        }

        return VehiclePostDTO.builder()
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .color(vehicle.getColor())
                .licensePlate(vehicle.getLicensePlate())
                .type(vehicle.getType())
                .build();
    }
}
