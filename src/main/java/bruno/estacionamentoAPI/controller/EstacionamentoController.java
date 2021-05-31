package bruno.estacionamentoAPI.controller;

import bruno.estacionamentoAPI.controller.dto.EstacionamentoDto;
import bruno.estacionamentoAPI.controller.form.EstacionamentoForm;
import bruno.estacionamentoAPI.model.Estacionamento;
import bruno.estacionamentoAPI.repository.EstacionamentoRepository;
import bruno.estacionamentoAPI.util.RespostaJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estacionamentos")
public class EstacionamentoController {

  @Autowired
  private EstacionamentoRepository repository;

  @GetMapping
  public ResponseEntity<List<EstacionamentoDto>> index() {
    return ResponseEntity.ok(EstacionamentoDto.converter(repository.findAll()));
  }

  @PostMapping
  public ResponseEntity<EstacionamentoDto> store(@RequestBody EstacionamentoForm form) {
    Estacionamento estacionamento = form.converter();
    repository.save(estacionamento);

    return ResponseEntity.status(201).body(EstacionamentoDto.converterUm(estacionamento));
  }

  @PutMapping("/{id}")
  public ResponseEntity<EstacionamentoDto> update(@PathVariable Long id, @RequestBody EstacionamentoForm form) {
    Estacionamento estacionamento = form.converter();
    estacionamento.setId(id);
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
