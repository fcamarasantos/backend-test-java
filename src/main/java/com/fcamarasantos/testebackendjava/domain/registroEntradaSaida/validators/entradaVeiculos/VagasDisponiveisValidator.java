package com.fcamarasantos.testebackendjava.domain.registroEntradaSaida.validators.entradaVeiculos;

import com.fcamarasantos.testebackendjava.domain.estabelecimento.EstabelecimentoRepository;
import com.fcamarasantos.testebackendjava.domain.registroEntradaSaida.RegistroEntradaSaidaRepository;
import com.fcamarasantos.testebackendjava.domain.registroEntradaSaida.dto.EntradaVeiculoDTO;
import com.fcamarasantos.testebackendjava.domain.veiculo.VeiculoRepository;
import com.fcamarasantos.testebackendjava.infra.exceptions.BusinessValidationException;
import jakarta.validation.ValidationException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VagasDisponiveisValidator implements EntradaVeiculosValidator {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private RegistroEntradaSaidaRepository registroEntradaSaidaRepository;

    @SneakyThrows
    @Override
    public void validate(EntradaVeiculoDTO data) {
        var veiculo = veiculoRepository
                .getReferenceById(data.idVeiculo());

        var estabelecimento = estabelecimentoRepository
                .getReferenceById(data.idEstabelecimento());

        var numVagasPreenchidas = registroEntradaSaidaRepository
                .getVagasDisponiveis(data.idEstabelecimento(), veiculo.getTipo());

        var numVagas = (veiculo.getTipo().name().equals("CARRO")) ?
                estabelecimento.getNumVagasCarros()
                : estabelecimento.getNumVagasMotos();

        if (numVagas - numVagasPreenchidas == 0) {
            throw new BusinessValidationException("Não há vagas disponíveis para veículos do tipo " + veiculo.getTipo().name());
        }
    }
}
