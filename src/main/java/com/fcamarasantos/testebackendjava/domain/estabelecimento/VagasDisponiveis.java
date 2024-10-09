package com.fcamarasantos.testebackendjava.domain.estabelecimento;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class VagasDisponiveis {
    private int numVagasMotos;
    private int numVagasCarros;

    public VagasDisponiveis(int numVagasMotos, int numVagasCarros) {
        this.numVagasMotos = numVagasMotos;
        this.numVagasCarros = numVagasCarros;
    }
}
