package fcamara.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import fcamara.controller.dto.ControleDto;
import fcamara.controller.dto.ControleEstacionadosDto;
import fcamara.controller.form.ControleForm;
import fcamara.model.Controle;
import fcamara.model.Estacionamento;
import fcamara.model.TipoVeiculo;
import fcamara.repository.ControleRepository;
import fcamara.repository.EstacionamentoRepository;
import fcamara.repository.VeiculoRepository;

@RestController
@RequestMapping("controle")
public class ControleController {
	
	@Autowired
	private ControleRepository controleRepository;
	@Autowired
	private EstacionamentoRepository estacionamentoRepository;
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@GetMapping
	public List<ControleDto> listar() {
		return ControleDto.converter(controleRepository.findAll());
	}
	
	@GetMapping("/{cnpj}/estacionados")
	public ResponseEntity<ControleEstacionadosDto> buscarEstacionados(@PathVariable String cnpj) {
		Estacionamento estacionamento = estacionamentoRepository.findByCnpj(cnpj);
		List<Controle> estacionados = controleRepository.findByCnpjAndDatahoraSaidaNull(cnpj);
		if(estacionamento == null || estacionados == null || estacionados.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body(new ControleEstacionadosDto(estacionamento, estacionados));
	}
	
	@GetMapping("/{cnpj}/historico")
	public ResponseEntity<ControleEstacionadosDto> buscarHistorico(@PathVariable String cnpj) {
		Estacionamento estacionamento = estacionamentoRepository.findByCnpj(cnpj);
		List<Controle> estacionados = controleRepository.findByCnpjAndDatahoraSaidaNotNull(cnpj);
		if(estacionamento == null || estacionados == null || estacionados.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body(new ControleEstacionadosDto(estacionamento, estacionados));
	}
	

	@PostMapping
	@Transactional
	public ResponseEntity<String> entrada(@RequestBody @Valid ControleForm form,UriComponentsBuilder uriBuilder) {
		Controle controle = form.converter(veiculoRepository, estacionamentoRepository);
		
		if(controle.getEstacionamento() == null)
			return ResponseEntity.status(404).body("Cnpj do estacionamento não encontrado!");
		
		if(controle.getVeiculo() == null)
			return ResponseEntity.status(404).body("Placa do veiculo não encontrado!");
		
		Controle jaEstaEstacionado = controleRepository
										.findByPlacaAndDatahoraSaidaNull(
												controle.getVeiculo().getPlaca()
												);
		if(jaEstaEstacionado != null) {
			return ResponseEntity.status(400).body("Veiculo com está placa ja se encontra estacionado no "+jaEstaEstacionado.getEstacionamento().getNome() + "!");
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
			return ResponseEntity.status(400).body("Não há vagas disponíveis no momento!");
		}
		
		return ResponseEntity.status(201).body("Veiculo estacionado!");
	}

	@PutMapping
	@Transactional
	public ResponseEntity<String> saida(@RequestBody @Valid ControleForm form,UriComponentsBuilder uriBuilder) {
		Controle controle = form.converter(veiculoRepository, estacionamentoRepository);
		
		if(controle.getEstacionamento() == null)
			return ResponseEntity.status(404).body("Cnpj do estacionamento não encontrado!");
		
		if(controle.getVeiculo() == null)
			return ResponseEntity.status(404).body("Placa do veiculo não encontrado!");
		
		Controle estaEstacionado = controleRepository
										.findByCnpjAndPlacaAndDatahoraSaidaNull(
												controle.getEstacionamento().getCnpj(),
												controle.getVeiculo().getPlaca()
												);
		if(estaEstacionado == null) {
			return ResponseEntity.status(404).body("Veiculo com está placa não está estacionado neste estacionamento!");
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
		
		return ResponseEntity.status(200).body("Volte Sempre!");
	}
	
	
	
	
	
}
