package com.fcamara.parking.api.domain.dto;

import com.fcamara.parking.api.domain.enums.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehiclePostDTO {
    private Long id;

    @NotBlank(message = "A marca é obrigatória")
    private String brand;

    @NotBlank(message = "O modelo é obrigatório")
    private String model;

    @NotBlank(message = "A cor é obrigatória")
    private String color;

    @NotNull
    @Size(min = 7, max = 7, message = "O mínimo e o máximo de caracteres é 7")
    private String licensePlate;

    @NotNull(message = "O tipo não pode ser nulo")
    private VehicleType type;
}