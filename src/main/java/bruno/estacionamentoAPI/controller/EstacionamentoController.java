package bruno.estacionamentoAPI.controller;

import bruno.estacionamentoAPI.config.security.TokenService;
import bruno.estacionamentoAPI.controller.dto.EstacionamentoDto;
import bruno.estacionamentoAPI.controller.form.EstacionamentoForm;
import bruno.estacionamentoAPI.model.Estacionamento;
import bruno.estacionamentoAPI.repository.EstacionamentoRepository;
import bruno.estacionamentoAPI.util.RespostaJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estacionamentos")
public class EstacionamentoController {

  @Autowired
  private EstacionamentoRepository repository;

  @Autowired
  private TokenService tokenService;

  @GetMapping
  public ResponseEntity<List<EstacionamentoDto>> index() {
    return ResponseEntity.ok(EstacionamentoDto.converter(repository.findAll()));
  }

  @PostMapping
  public ResponseEntity<?> store(@RequestBody EstacionamentoForm form) {
    Optional<Estacionamento> estacionamentoDb = repository.findByEmail(form.getEmail());
    if (estacionamentoDb.isPresent()) return ResponseEntity.badRequest().body(RespostaJson.mensagem("Estacionamento ja cadastrado"));
    Estacionamento estacionamento = form.converter();
    repository.save(estacionamento);

    return ResponseEntity.status(201).body(EstacionamentoDto.converterUm(estacionamento));
  }

  @PutMapping
  public ResponseEntity<EstacionamentoDto> update(@RequestHeader("Authorization") String token, @RequestBody EstacionamentoForm form) {
    Long estacionamentoId = tokenService.getUsuarioId(token.substring(7, token.length()));
    Estacionamento estacionamento = form.converter();
    estacionamento.setId(estacionamentoId);
    repository.save(estacionamento);

    return ResponseEntity.status(200).body(EstacionamentoDto.converterUm(estacionamento));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> destroy(@PathVariable Long id) {
    Optional<Estacionamento> estacionamento = repository.findById(id);
    if (estacionamento.isPresent()) {
      repository.delete(estacionamento.get());
      return ResponseEntity.status(200).body(RespostaJson.mensagem("Estacionamento excluido com sucesso"));
    }
    return ResponseEntity.status(404).body(RespostaJson.mensagem("Não foi possivel encontrar o estacionamento"));
  }
}
