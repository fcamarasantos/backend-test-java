package com.fcamarasantos.testebackendjava.domain.veiculo.dto;

import com.fcamarasantos.testebackendjava.domain.veiculo.TipoVeiculo;
import com.fcamarasantos.testebackendjava.domain.veiculo.Veiculo;

public record VeiculoDetailsDTO(
        Long id,
        String marca,
        String modelo,
        String placa,
        String cor,
        TipoVeiculo tipo
) {

    public VeiculoDetailsDTO(Veiculo veiculo) {
        this(
                veiculo.getId(),
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getPlaca(),
                veiculo.getCor(),
                veiculo.getTipo()
        );
    }
}
