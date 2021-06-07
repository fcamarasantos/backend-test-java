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
import fcamara.model.entity.Controle;
import fcamara.model.entity.Estacionamento;
import fcamara.model.entity.TipoVeiculo;
import fcamara.model.service.ControleService;

@RestController
@RequestMapping("controle")
public class ControleController {
	
	@Autowired
	private ControleService controleService;
	
	@GetMapping
	public List<ControleDto> listar() {
		return controleService.listar();
	}
	
	@GetMapping("/{cnpj}/estacionados")
	public ResponseEntity<ControleEstacionadosDto> buscarEstacionados(@PathVariable String cnpj) {
		ControleEstacionadosDto controle = controleService.buscarEstacionados(cnpj);
		if(controle == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body(controle);
	}
	
	@GetMapping("/{cnpj}/historico")
	public ResponseEntity<ControleEstacionadosDto> buscarHistorico(@PathVariable String cnpj) {
		ControleEstacionadosDto controle = controleService.buscarHistorico(cnpj);
		if(controle == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body(controle);
	}
	

	@PostMapping
	@Transactional
	public ResponseEntity<String> entrada(@RequestBody @Valid ControleForm form) {
		String msg  = controleService.entrada(form);
		
		switch(msg) {
		case "Cnpj do estacionamento não encontrado!":
			return ResponseEntity.status(404).body("{\"msg\":\""+msg+"\"}");
		case "Placa do veiculo não encontrado!":
			return ResponseEntity.status(404).body("{\"msg\":\""+msg+"\"}");
		case "Não há vagas disponíveis no momento!":
			return ResponseEntity.status(400).body("{\"msg\":\""+msg+"\"}");
		case "Veiculo estacionado!":
			return ResponseEntity.status(200).body("{\"msg\":\""+msg+"\"}");
		default:
			return ResponseEntity.status(400).body("{\"msg\":\""+msg+"\"}");
		}
	}

	@PutMapping
	@Transactional
	public ResponseEntity<String> saida(@RequestBody @Valid ControleForm form) {
		String msg  = controleService.saida(form);
		
		switch(msg) {
		case "Cnpj do estacionamento não encontrado!":
			return ResponseEntity.status(404).body("{\"msg\":\""+msg+"\"}");
		case "Placa do veiculo não encontrado!":
			return ResponseEntity.status(404).body("{\"msg\":\""+msg+"\"}");
		case "Veiculo com está placa não está estacionado neste estacionamento!":
			return ResponseEntity.status(404).body("{\"msg\":\""+msg+"\"}");
		default:
			return ResponseEntity.status(200).body("{\"msg\":\""+msg+"\"}");
		}
	}
	
	
	
	
	
}
