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


import fcamara.controller.dto.VeiculoDto;
import fcamara.controller.form.VeiculoForm;
import fcamara.model.entity.Veiculo;
import fcamara.model.service.VeiculoService;

@RestController
@RequestMapping("veiculo")
public class VeiculoController {

	@Autowired
	private VeiculoService veiculoService;
	
	@GetMapping
	public ResponseEntity<List<VeiculoDto>> listar() {
		List<VeiculoDto> veiculos = veiculoService.listar();
		if(veiculos == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(veiculos);
	}
	
	@GetMapping("/{placa}")
	public ResponseEntity<VeiculoDto> buscar(@PathVariable String placa) {
		Veiculo veiculo = veiculoService.buscar(placa);
		if(veiculo == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(new VeiculoDto(veiculo));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid VeiculoForm form,UriComponentsBuilder uriBuilder) {
		Veiculo veiculo = form.converter();
		String msg = veiculoService.cadastrar(veiculo);
		if(msg == "Cadastrado com Sucesso!") { 
			VeiculoDto v = new VeiculoDto(veiculo);
			v.setMsg(msg);
			URI uri = uriBuilder.path("veiculo/{placa}").buildAndExpand(veiculo.getPlaca()).toUri();
			return ResponseEntity.created(uri).body(v);
		}
		else {
			msg = "{\"msg\":\""+msg+"\"}";
			return ResponseEntity.badRequest().body(msg);
		}
		
	}
		
	@DeleteMapping("/{placa}")
	@Transactional
	public ResponseEntity<String> deletar(@PathVariable String placa) {
		String msg = veiculoService.deletar(placa);
		
		if(msg != "Veiculo deletado com Sucesso!") {
			msg = "{\"msg\":\""+msg+"\"}";
			return ResponseEntity.badRequest().body(msg);
		}
		msg = "{\"msg\":\""+msg+"\"}";
		return ResponseEntity.ok().body(msg);
		
	}
	
	@PutMapping("/{placa}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable String placa, @RequestBody @Valid VeiculoForm form) {
		Veiculo veiculo = form.converter();
		String msg = veiculoService.atualizar(placa, form);
		
		if(msg != "Veiculo atualizado com Sucesso!") {
			msg = "{\"msg\":\""+msg+"\"}";
			return ResponseEntity.badRequest().body(msg);
		}
		
		VeiculoDto v = new VeiculoDto(veiculo);
		v.setMsg(msg);
		return ResponseEntity.ok().body(v);
		
	}
	
	
	
}
