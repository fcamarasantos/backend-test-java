package com.fcamarasantos.testebackendjava.domain.veiculo.dto;

import com.fcamarasantos.testebackendjava.domain.veiculo.TipoVeiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateVeiculoDTO(
        String marca,
        String modelo,
        String placa,
        String cor
) {
}