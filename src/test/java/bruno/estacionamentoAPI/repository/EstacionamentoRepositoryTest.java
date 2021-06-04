package bruno.estacionamentoAPI.repository;

import bruno.estacionamentoAPI.model.Estacionamento;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EstacionamentoRepositoryTest {

  @Autowired
  private EstacionamentoRepository repository;

  @Test
  void deveriaAcharUmEstacionamentoPeloEmail() {
    String email = "teste@teste.com.br";
    Optional<Estacionamento> estacionamento = repository.findByEmail(email);
    assertNotNull(estacionamento);
    assertEquals("Estacionamento Laranja", estacionamento.get().getNome());
  }

}