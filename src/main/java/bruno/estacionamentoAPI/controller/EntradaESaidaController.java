package bruno.estacionamentoAPI.controller;

import bruno.estacionamentoAPI.config.security.TokenService;
import bruno.estacionamentoAPI.model.Estacionamento;
import bruno.estacionamentoAPI.model.Veiculo;
import bruno.estacionamentoAPI.repository.EstacionamentoRepository;
import bruno.estacionamentoAPI.repository.VeiculosRepository;
import bruno.estacionamentoAPI.service.ControleService;
import bruno.estacionamentoAPI.util.RespostaJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/controle")
public class EntradaESaidaController {

  @Autowired
  private EstacionamentoRepository estacionamentoRepository;

  @Autowired
  private VeiculosRepository veiculosRepository;

  @Autowired
  private TokenService tokenService;

  @Autowired
  private ControleService controleService;

  @PostMapping("/entrada/{veiculoId}")
  public ResponseEntity<String> entrada(@RequestHeader("Authorization") String token, @PathVariable Long veiculoId) {
    Long estacionamentoId = tokenService.getUsuarioId(token.substring(7, token.length()));
    Estacionamento estacionamento = estacionamentoRepository.findById(estacionamentoId).orElse(null);
    Optional<Veiculo> veiculo = veiculosRepository.findById(veiculoId);
    if(veiculo.isPresent()) {
      boolean isEstacionado =  controleService.estacionar(veiculo.get(), estacionamento);

      if(isEstacionado) return ResponseEntity.ok().body(RespostaJson.mensagem("Veiculo estácionado"));
      return ResponseEntity.badRequest().body(RespostaJson.mensagem("Estacionamento atingiu a capacidade máxima"));
    }
    return ResponseEntity.badRequest().body(RespostaJson.mensagem("Veiculo não cadastrado"));
  }

  @PostMapping("/retirada/{veiculoId}")
  public ResponseEntity<String> retirada(@RequestHeader("Authorization") String token, @PathVariable Long veiculoId) {
    Long estacionamentoId = tokenService.getUsuarioId(token.substring(7, token.length()));
    Estacionamento estacionamento = estacionamentoRepository.findById(estacionamentoId).orElse(null);
    Optional<Veiculo> veiculo = veiculosRepository.findById(veiculoId);
    if(veiculo.isPresent()) {
      boolean isEstacionado = controleService.retirar(veiculo.get(), estacionamento);

      if(isEstacionado) return ResponseEntity.ok().body(RespostaJson.mensagem("Veiculo retirado"));
    }
    return ResponseEntity.badRequest().body(RespostaJson.mensagem("Veiculo não encontrado"));
  }
}
