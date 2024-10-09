package com.fcamarasantos.testebackendjava.domain.registroEntradaSaida;

import com.fcamarasantos.testebackendjava.domain.estabelecimento.VagasDisponiveis;
import com.fcamarasantos.testebackendjava.domain.registroEntradaSaida.dto.EntradaVeiculoDTO;
import com.fcamarasantos.testebackendjava.domain.registroEntradaSaida.dto.SaidaVeiculoDTO;
import com.fcamarasantos.testebackendjava.domain.estabelecimento.EstabelecimentoRepository;
import com.fcamarasantos.testebackendjava.domain.registroEntradaSaida.validators.entradaVeiculos.EntradaVeiculosValidator;
import com.fcamarasantos.testebackendjava.domain.registroEntradaSaida.validators.saidaVeiculos.SaidaVeiculosValidator;
import com.fcamarasantos.testebackendjava.domain.veiculo.TipoVeiculo;
import com.fcamarasantos.testebackendjava.domain.veiculo.VeiculoRepository;
import com.fcamarasantos.testebackendjava.infra.exceptions.BusinessValidationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistroEntradaSaidaService {

    @Autowired
    private RegistroEntradaSaidaRepository controleEstabelecimentoRepository;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private List<EntradaVeiculosValidator> entradaVeiculosValidators;

    @Autowired
    private List<SaidaVeiculosValidator> saidaVeiculosValidators;

    public void entradaVeiculo(EntradaVeiculoDTO entradaVeiculoDTO) {
        var idEstabelecimento = entradaVeiculoDTO.idEstabelecimento();
        var idVeiculo = entradaVeiculoDTO.idVeiculo();

        entradaVeiculosValidators.forEach(validator -> validator.validate(entradaVeiculoDTO));

        var estabelecimento = estabelecimentoRepository.getReferenceById(idEstabelecimento);
        var veiculo = veiculoRepository.getReferenceById(idVeiculo);

        var controleEstabelecimento = new RegistroEntradaSaida(
                null,
                estabelecimento,
                veiculo,
                LocalDateTime.now(),
                null
        );

        controleEstabelecimentoRepository.save(controleEstabelecimento);

        System.out.printf("Entrada de veículo %s no estabelecimento %s em %s \n", idVeiculo, idEstabelecimento, LocalDateTime.now());
    }

    public void saidaVeiculo(SaidaVeiculoDTO saidaVeiculoDTO) {
        var idEstabelecimento = saidaVeiculoDTO.idEstabelecimento();
        var idVeiculo = saidaVeiculoDTO.idVeiculo();

        saidaVeiculosValidators.forEach(validator -> validator.validate(saidaVeiculoDTO));

        var controleEstabelecimento = getControleEstabelecimento(
                saidaVeiculoDTO.idEstabelecimento(),
                saidaVeiculoDTO.idVeiculo()
        );

        if (controleEstabelecimento == null) {
            throw new BusinessValidationException("Veiculo não encontrado em nenhum estabelecimento");
        }

        controleEstabelecimento.setDataSaida(LocalDateTime.now());

        System.out.printf("Saída de veículo %s do estabelecimento %s em %s \n", idVeiculo, idEstabelecimento, LocalDateTime.now());
    }

    private RegistroEntradaSaida getControleEstabelecimento(Long idEstabelecimento, Long idVeiculo) {
        var controleEstabelecimento = controleEstabelecimentoRepository
                .buscarRegistroRelacionado(
                        idEstabelecimento, idVeiculo
                );

        return controleEstabelecimento;
    }

    public VagasDisponiveis vagasDisponiveis(@Valid Long idEstabelecimento) {
        var estabelecimento = estabelecimentoRepository
                .getReferenceById(idEstabelecimento);

        var vagasCarrosPreenchidas = controleEstabelecimentoRepository
                .getVagasDisponiveis(idEstabelecimento, TipoVeiculo.CARRO);

        var vagasMotosPreenchidas = controleEstabelecimentoRepository
                .getVagasDisponiveis(idEstabelecimento, TipoVeiculo.MOTO);

        return new VagasDisponiveis(
                estabelecimento.getNumVagasMotos() - vagasMotosPreenchidas,
                estabelecimento.getNumVagasCarros() - vagasCarrosPreenchidas
        );
    }
}
