package com.fcamarasantos.testebackendjava.domain.registroEntradaSaida.validators.entradaVeiculos;

import com.fcamarasantos.testebackendjava.domain.estabelecimento.EstabelecimentoRepository;
import com.fcamarasantos.testebackendjava.domain.registroEntradaSaida.dto.EntradaVeiculoDTO;
import com.fcamarasantos.testebackendjava.domain.veiculo.VeiculoRepository;
import com.fcamarasantos.testebackendjava.infra.exceptions.BusinessValidationException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("VerificaEntidadesEntradaVeiculosValidator")
public class VerificaEntidadesValidator implements EntradaVeiculosValidator {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Override
    public void validate(EntradaVeiculoDTO data) {
        var veiculo = veiculoRepository
                .findById(data.idVeiculo());

        var estabelecimento = estabelecimentoRepository
                .findById(data.idEstabelecimento());


        if (veiculo.isEmpty()) {
            throw new BusinessValidationException("Veículo não encontrado");
        }

        if (estabelecimento.isEmpty()) {
            throw new BusinessValidationException("Estabelecimento não encontrado");
        }

    }
}
