package com.fcamarasantos.testebackendjava.domain.registroEntradaSaida.dto;

import java.time.LocalDateTime;

public record SaidaVeiculoDetailsDTO(
        Long idVeiculo,

        Long idEstabelecimento,

        LocalDateTime dataEntrada,

        LocalDateTime dataSaida
) {
}
