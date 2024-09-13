package com.fcamara.parking.api.mappers;

import com.fcamara.parking.api.domain.dto.EstablishmentPostDTO;
import com.fcamara.parking.api.domain.entities.Establishment;

public class EstablishmentMapper {
    public static Establishment toEntity(EstablishmentPostDTO dto) {
        if (dto == null) {
            return null;
        }
        return Establishment.builder()
                .name(dto.getName())
                .cnpj(dto.getCnpj())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .motorcycleSpots(dto.getMotorcycleSpots())
                .carSpots(dto.getCarSpots())
                .build();
    }

    public static EstablishmentPostDTO toDTO(Establishment establishment) {
        if (establishment == null) {
            return null;
        }
        return EstablishmentPostDTO.builder()
                .name(establishment.getName())
                .cnpj(establishment.getCnpj())
                .address(establishment.getAddress())
                .phone(establishment.getPhone())
                .motorcycleSpots(establishment.getMotorcycleSpots())
                .carSpots(establishment.getCarSpots())
                .build();
    }
}
