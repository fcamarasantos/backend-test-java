package bruno.estacionamentoAPI.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RespostaJsonTest {

  @Test
  void deveriaRetornarUmaStringFormatadaEmJson() {
    String json = RespostaJson.mensagem("Teste passou");

    assertEquals("{\"mensagem\":\"Teste passou\"}", json);
  }
}
