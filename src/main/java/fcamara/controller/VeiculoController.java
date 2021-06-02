package fcamara.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import fcamara.controller.dto.VeiculoDto;
import fcamara.controller.form.VeiculoForm;
import fcamara.model.Veiculo;
import fcamara.repository.VeiculoRepository;

@RestController
@RequestMapping("veiculo")
public class VeiculoController {
	
	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@GetMapping
	public List<VeiculoDto> listar() {
		return VeiculoDto.converter(veiculoRepository.findAll());
	}
	
	@GetMapping("/{placa}")
	public Veiculo buscar(@PathVariable String placa) {
		return veiculoRepository.findByPlaca(placa);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<VeiculoDto> cadastrar(@RequestBody @Valid VeiculoForm form,UriComponentsBuilder uriBuilder) {
		Veiculo veiculo = form.converter();
		veiculoRepository.save(veiculo);
		
		URI uri = uriBuilder.path("veiculo/{placa}").buildAndExpand(veiculo.getPlaca()).toUri();
		return ResponseEntity.created(uri).body(new VeiculoDto(veiculo));
	}
		
	@DeleteMapping("/{placa}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable String placa) {
		Veiculo veiculo = veiculoRepository.findByPlaca(placa);
		if(veiculo == null) {
			return ResponseEntity.notFound().build();
		}
		
		veiculoRepository.deleteByPlaca(placa);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{placa}")
	@Transactional
	public ResponseEntity<VeiculoDto> atualizar(@PathVariable String placa, @RequestBody @Valid VeiculoForm form) {
		Veiculo veiculo = veiculoRepository.findByPlaca(placa);
		if(veiculo == null) {
			return ResponseEntity.notFound().build();
		}
		
		Veiculo atualizado = form.atualizar(placa, veiculoRepository);
		return ResponseEntity.ok(new VeiculoDto(atualizado));
	}
	
	
	
}
