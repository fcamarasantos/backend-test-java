package com.fcamarasantos.testebackendjava.domain.registroEntradaSaida.validators.saidaVeiculos;

import com.fcamarasantos.testebackendjava.domain.estabelecimento.EstabelecimentoRepository;
import com.fcamarasantos.testebackendjava.domain.registroEntradaSaida.dto.EntradaVeiculoDTO;
import com.fcamarasantos.testebackendjava.domain.registroEntradaSaida.dto.SaidaVeiculoDTO;
import com.fcamarasantos.testebackendjava.domain.veiculo.VeiculoRepository;
import com.fcamarasantos.testebackendjava.infra.exceptions.BusinessValidationException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("VerificaEntidadesSaidaVeiculosValidator")
public class VerificaEntidadesValidator implements SaidaVeiculosValidator {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Override
    public void validate(SaidaVeiculoDTO data) {
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
