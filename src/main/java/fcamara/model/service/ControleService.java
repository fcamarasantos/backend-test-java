package fcamara.model.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import fcamara.controller.dto.ControleDto;
import fcamara.controller.dto.ControleEstacionadosDto;
import fcamara.controller.form.ControleForm;
import fcamara.model.entity.Controle;
import fcamara.model.entity.Estacionamento;
import fcamara.model.entity.TipoVeiculo;
import fcamara.model.repository.ControleRepository;
import fcamara.model.repository.EstacionamentoRepository;
import fcamara.model.repository.VeiculoRepository;

@Service
public class ControleService {
	
	private ControleRepository controleRepository;
	private EstacionamentoRepository estacionamentoRepository;
	private VeiculoRepository veiculoRepository;
	
	
	@Autowired
	public ControleService(ControleRepository controleRepository, EstacionamentoRepository estacionamentoRepository,
			VeiculoRepository veiculoRepository) {
		this.controleRepository = controleRepository;
		this.estacionamentoRepository = estacionamentoRepository;
		this.veiculoRepository = veiculoRepository;
	}

	public List<ControleDto> listar() {
		return ControleDto.converter(controleRepository.findAll());
	}

	public ControleEstacionadosDto buscarEstacionados(String cnpj) {
		Estacionamento estacionamento = estacionamentoRepository.findByCnpj(cnpj);
		List<Controle> estacionados = controleRepository.findByCnpjAndDatahoraSaidaNull(cnpj);
		if(estacionamento == null || estacionados == null || estacionados.isEmpty())
			return null;
		
		return new ControleEstacionadosDto(estacionamento, estacionados);
	}
	

	public ControleEstacionadosDto buscarHistorico(String cnpj) {
		Estacionamento estacionamento = estacionamentoRepository.findByCnpj(cnpj);
		List<Controle> estacionados = controleRepository.findByCnpjAndDatahoraSaidaNotNull(cnpj);
		if(estacionamento == null || estacionados == null || estacionados.isEmpty())
			return null;
		
		return new ControleEstacionadosDto(estacionamento, estacionados);
	}
	

	public String entrada(ControleForm form) {
		Controle controle = form.converter(veiculoRepository, estacionamentoRepository);
		
		if(controle.getEstacionamento() == null)
			return "Cnpj do estacionamento não encontrado!";
		
		if(controle.getVeiculo() == null)
			return "Placa do veiculo não encontrado!";
		
		Controle jaEstaEstacionado = controleRepository
										.findByPlacaAndDatahoraSaidaNull(
												controle.getVeiculo().getPlaca()
												);
		if(jaEstaEstacionado != null) {
			return "Veiculo com está placa ja se encontra estacionado no "+jaEstaEstacionado.getEstacionamento().getNome() + "!";
		}
		
		if(controle.getVeiculo().getTipo() == TipoVeiculo.CARRO && controle.getEstacionamento().getQtd_carro() > 0)
		{
			controle.getEstacionamento().setQtd_carro(controle.getEstacionamento().getQtd_carro() - 1);
			estacionamentoRepository.save(controle.getEstacionamento());
			controleRepository.save(controle);
		}
		else if(controle.getVeiculo().getTipo() == TipoVeiculo.MOTO && controle.getEstacionamento().getQtd_moto() > 0) {
			controle.getEstacionamento().setQtd_moto(controle.getEstacionamento().getQtd_moto() - 1);
			estacionamentoRepository.save(controle.getEstacionamento());
			controleRepository.save(controle);
		}
		else {
			return "Não há vagas disponíveis no momento!";
		}
		
		return "Veiculo estacionado!";
	}

	
	public String saida(ControleForm form) {
		Controle controle = form.converter(veiculoRepository, estacionamentoRepository);
		
		if(controle.getEstacionamento() == null)
			return "Cnpj do estacionamento não encontrado!";
		
		if(controle.getVeiculo() == null)
			return "Placa do veiculo não encontrado!";
		
		Controle estaEstacionado = controleRepository
										.findByCnpjAndPlacaAndDatahoraSaidaNull(
												controle.getEstacionamento().getCnpj(),
												controle.getVeiculo().getPlaca()
												);
		if(estaEstacionado == null) {
			return "Veiculo com está placa não está estacionado neste estacionamento!";
		}
		
		if(estaEstacionado.getVeiculo().getTipo() == TipoVeiculo.CARRO)
		{
			estaEstacionado.getEstacionamento().setQtd_carro(estaEstacionado.getEstacionamento().getQtd_carro() + 1);
			estaEstacionado.setDatahora_saida(LocalDateTime.now());
			estacionamentoRepository.save(estaEstacionado.getEstacionamento());
			controleRepository.save(estaEstacionado);
		}
		else if(estaEstacionado.getVeiculo().getTipo() == TipoVeiculo.MOTO) {
			estaEstacionado.getEstacionamento().setQtd_moto(controle.getEstacionamento().getQtd_moto() + 1);
			estaEstacionado.setDatahora_saida(LocalDateTime.now());
			estacionamentoRepository.save(estaEstacionado.getEstacionamento());
			controleRepository.save(estaEstacionado);
		}
		
		return "Volte Sempre!";
	}
}
