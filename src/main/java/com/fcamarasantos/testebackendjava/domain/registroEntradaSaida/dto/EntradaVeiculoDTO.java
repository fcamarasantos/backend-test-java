package com.fcamarasantos.testebackendjava.domain.registroEntradaSaida.dto;

import jakarta.validation.constraints.NotNull;

public record EntradaVeiculoDTO(
        @NotNull
        Long idVeiculo,

        @NotNull
        Long idEstabelecimento
) {
}
