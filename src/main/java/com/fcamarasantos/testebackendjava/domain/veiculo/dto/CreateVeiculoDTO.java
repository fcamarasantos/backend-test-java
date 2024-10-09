package com.fcamarasantos.testebackendjava.domain.veiculo.dto;

import com.fcamarasantos.testebackendjava.domain.veiculo.TipoVeiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateVeiculoDTO(
        @NotBlank
        String marca,

        @NotBlank
        String modelo,

        @NotBlank
        String placa,

        @NotBlank
        String cor,

        @NotNull
        TipoVeiculo tipo
) {
}
