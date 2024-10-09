package com.fcamarasantos.testebackendjava.domain.registroEntradaSaida.validators.entradaVeiculos;

import com.fcamarasantos.testebackendjava.domain.registroEntradaSaida.RegistroEntradaSaidaRepository;
import com.fcamarasantos.testebackendjava.domain.registroEntradaSaida.dto.EntradaVeiculoDTO;
import com.fcamarasantos.testebackendjava.infra.exceptions.BusinessValidationException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerificaJaEstacionadoValidator implements EntradaVeiculosValidator {

    @Autowired
    private RegistroEntradaSaidaRepository registroEntradaSaidaRepository;

    @Override
    public void validate(EntradaVeiculoDTO data) {
        var estaEstacionado = registroEntradaSaidaRepository
                .existsByVeiculoIdAndDataSaidaIsNull(data.idVeiculo());

        if (estaEstacionado) {
            throw new BusinessValidationException("Veículo já estacionado, o mesmo deve ser retirado antes de estacionar novamente em um estabelecimento");
        }
    }
}
