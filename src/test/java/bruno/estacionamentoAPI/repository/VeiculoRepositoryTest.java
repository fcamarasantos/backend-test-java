package bruno.estacionamentoAPI.repository;

import bruno.estacionamentoAPI.model.Estacionamento;
import bruno.estacionamentoAPI.model.Veiculo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VeiculoRepositoryTest {

  @Autowired
  private VeiculosRepository repository;

  @Autowired
  private EstacionamentoRepository estacionamentoRepository;

  @Test
  void deveriaAcharUmVeiculoPelaPlacaEEstacionamento() {
    String placa = "FOW3689";
    Estacionamento estacionamento = estacionamentoRepository.findById(Long.valueOf(1)).orElse(null);
    Veiculo veiculo = repository.findByPlacaAndEstacionamento(placa, estacionamento).orElse(null);
    assertEquals("Fiat", veiculo.getMarca());
    assertEquals("Cinza", veiculo.getCor());
  }
}
