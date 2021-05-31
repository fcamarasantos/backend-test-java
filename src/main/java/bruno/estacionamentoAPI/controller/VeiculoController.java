package bruno.estacionamentoAPI.controller;

import bruno.estacionamentoAPI.controller.dto.VeiculoDto;
import bruno.estacionamentoAPI.controller.form.VeiculoForm;
import bruno.estacionamentoAPI.model.Estacionamento;
import bruno.estacionamentoAPI.model.Veiculo;
import bruno.estacionamentoAPI.repository.EstacionamentoRepository;
import bruno.estacionamentoAPI.repository.VeiculosRepository;
import bruno.estacionamentoAPI.util.RespostaJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

  @Autowired
  private VeiculosRepository repository;

  @Autowired
  private EstacionamentoRepository estacionamentoRepository;

  @GetMapping
  public ResponseEntity<List<VeiculoDto>> getAll() {
    return ResponseEntity.ok(VeiculoDto.converter(repository.findAll()));
  }

  @PostMapping("/{estacionamentoId}")
  public ResponseEntity<?> store(@PathVariable Long estacionamentoId, @RequestBody VeiculoForm form) {
    Optional<Estacionamento> estacionamento = estacionamentoRepository.findById(estacionamentoId);
    if (estacionamento.isPresent()) {
      Veiculo veiculo = form.converter();
      veiculo.setEstacionamento(estacionamento.get());
      repository.save(veiculo);
      return ResponseEntity.status(201).body(VeiculoDto.converterUm(veiculo));
    }
    return ResponseEntity.status(404).body(RespostaJson.mensagem("Estacionamento com id " + estacionamentoId + " não existe"));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody VeiculoForm form) {
    Veiculo veiculo = form.converter();
    Optional<Estacionamento> estacionamento = estacionamentoRepository.findById(form.getEstacionamentoId());
    if (estacionamento.isPresent()) {
      veiculo.setId(id);
      veiculo.setEstacionamento(estacionamento.get());
      repository.save(veiculo);
      return ResponseEntity.status(200).body(VeiculoDto.converterUm(veiculo));
    }
    return ResponseEntity.status(404).body(RespostaJson.mensagem("Estacionamento não encontrado"));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> destroy(@PathVariable Long id) {
    Optional<Veiculo> veiculo = repository.findById(id);
    if (veiculo.isPresent()) {
      repository.delete(veiculo.get());
      return ResponseEntity.status(200).body(RespostaJson.mensagem("Veiculo excluido com sucesso"));
    }
    return ResponseEntity.status(404).body(RespostaJson.mensagem("Não foi possivel encontrar o veiculo"));
  }
}
