package com.fcamarasantos.testebackendjava.domain.registroEntradaSaida.dto;

import java.time.LocalDateTime;

public record EntradaVeiculoDetailsDTO(
        Long idVeiculo,

        Long idEstabelecimento,

        LocalDateTime dataEntrada
) {
}
