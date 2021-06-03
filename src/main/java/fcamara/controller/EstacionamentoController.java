package fcamara.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import fcamara.controller.dto.EstacionamentoDto;
import fcamara.controller.dto.VeiculoDto;
import fcamara.controller.form.EstacionamentoForm;
import fcamara.controller.form.VeiculoForm;
import fcamara.model.Estacionamento;
import fcamara.model.Veiculo;
import fcamara.repository.EstacionamentoRepository;

@RestController
@RequestMapping("estacionamento")
public class EstacionamentoController {
	
	@Autowired
	private EstacionamentoRepository estacionamentoRepository;
	
	@GetMapping
	public List<EstacionamentoDto> listar() {
		return EstacionamentoDto.converter(estacionamentoRepository.findAll());
	}
	
	@GetMapping("/{cnpj}")
	public Estacionamento buscar(@PathVariable String cnpj) {
		return estacionamentoRepository.findByCnpj(cnpj);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<EstacionamentoDto> cadastrar(@RequestBody @Valid EstacionamentoForm form,UriComponentsBuilder uriBuilder) {
		Estacionamento estacionamento = form.converter();
		estacionamentoRepository.save(estacionamento);
		
		URI uri = uriBuilder.path("estacionamento/{cnpj}").buildAndExpand(estacionamento.getCnpj()).toUri();
		return ResponseEntity.created(uri).body(new EstacionamentoDto(estacionamento));
	}
		
	@DeleteMapping("/{cnpj}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable String cnpj) {
		Estacionamento estacionamento = estacionamentoRepository.findByCnpj(cnpj);
		if(estacionamento == null) {
			return ResponseEntity.notFound().build();
		}
		
		estacionamentoRepository.deleteByCnpj(cnpj);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{cnpj}")
	@Transactional
	public ResponseEntity<EstacionamentoDto> atualizar(@PathVariable String cnpj, @RequestBody @Valid EstacionamentoForm form) {
		Estacionamento estacionamento = estacionamentoRepository.findByCnpj(cnpj);
		if(estacionamento == null) {
			return ResponseEntity.notFound().build();
		}
		
		Estacionamento atualizado = form.atualizar(cnpj, estacionamentoRepository);
		return ResponseEntity.ok(new EstacionamentoDto(atualizado));
	}
	
	
	
}
