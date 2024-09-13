package com.fcamara.parking.api.domain.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class ParkingPostDTO {
    @NotBlank(message = "O veículo é obrigatório")
    private Long vehicleId;
    @NotBlank(message = "O estabelecimento é obrigatório")
    private Long establishmentId;
    @NotBlank(message = "A entrada é obrigatória")
    private LocalDateTime entryTime;
}
