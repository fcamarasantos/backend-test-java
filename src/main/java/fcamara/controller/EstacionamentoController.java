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
import fcamara.model.entity.Estacionamento;
import fcamara.model.service.EstacionamentoService;

@RestController
@RequestMapping("estacionamento")
public class EstacionamentoController {
	
	@Autowired
	private EstacionamentoService estacionamentoService;
	
	
	@GetMapping
	public ResponseEntity<List<EstacionamentoDto>> listar() {
		List<EstacionamentoDto> estacionamentos = estacionamentoService.listar();
		if(estacionamentos == null) 
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(estacionamentos);
	}
	
	@GetMapping("/{cnpj}")
	public ResponseEntity<EstacionamentoDto> buscar(@PathVariable String cnpj) {
		Estacionamento estacionamento = estacionamentoService.buscar(cnpj);
		if(estacionamento == null) 
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(new EstacionamentoDto(estacionamento));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid EstacionamentoForm form,UriComponentsBuilder uriBuilder) {
		Estacionamento estacionamento = form.converter();
		String msg = estacionamentoService.cadastrar(estacionamento);
		if(msg == "Cadastrado com Sucesso!") {
			EstacionamentoDto e = new EstacionamentoDto(estacionamento);
			e.setMsg(msg);
			URI uri = uriBuilder.path("estacionamento/{cnpj}").buildAndExpand(estacionamento.getCnpj()).toUri();
			return ResponseEntity.created(uri).body(e);
		}
		else {
			msg = "{\"msg\":\""+msg+"\"}";
			return ResponseEntity.badRequest().body(msg);
		}
		
	}
		
	@DeleteMapping("/{cnpj}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable String cnpj) {
		String msg = estacionamentoService.deletar(cnpj);
		if(msg != "Estacionamento deletado com Sucesso!") {
			msg = "{\"msg\":\""+msg+"\"}";
			return ResponseEntity.badRequest().body(msg);
		}
		msg = "{\"msg\":\""+msg+"\"}";
		return ResponseEntity.ok().body(msg);
		
	}
	
	@PutMapping("/{cnpj}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable String cnpj, @RequestBody @Valid EstacionamentoForm form) {
		Estacionamento estacionamento = form.converter();
		String msg = estacionamentoService.atualizar(cnpj, form);
		
		if(msg != "Estacionamento atualizado com Sucesso!") {
			msg = "{\"msg\":\""+msg+"\"}";
			return ResponseEntity.badRequest().body(msg);
		}
		
		EstacionamentoDto e = new EstacionamentoDto(estacionamento);
		e.setMsg(msg);
		return ResponseEntity.ok().body(e);
		
	}
	
	
	
}
