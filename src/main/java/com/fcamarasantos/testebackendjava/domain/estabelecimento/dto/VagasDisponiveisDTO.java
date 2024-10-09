package com.fcamarasantos.testebackendjava.domain.estabelecimento.dto;

import com.fcamarasantos.testebackendjava.domain.estabelecimento.VagasDisponiveis;

public record VagasDisponiveisDTO(
        int numVagasMotos,
        int numVagasCarros
) {
    public VagasDisponiveisDTO(
            VagasDisponiveis vagasDisponiveis
    ) {
        this(vagasDisponiveis.getNumVagasMotos(), vagasDisponiveis.getNumVagasCarros());
    }
}
