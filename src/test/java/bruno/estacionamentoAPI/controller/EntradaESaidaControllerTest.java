package bruno.estacionamentoAPI.controller;

import bruno.estacionamentoAPI.config.security.TokenService;
import bruno.estacionamentoAPI.repository.EstacionamentoRepository;
import org.aspectj.lang.annotation.Before;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EntradaESaidaControllerTest {

  private String token;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private TokenService tokenService;

  @Autowired
  private AuthenticationManager authManager;

  @BeforeAll
  void login() {
    UsernamePasswordAuthenticationToken dadosLogin = new UsernamePasswordAuthenticationToken("teste@teste.com.br", "1234");
    Authentication authentication = authManager.authenticate(dadosLogin);
    token = "Bearer " + tokenService.gerarToken(authentication);
  }

  @Test
  void deveriaEstacionarUmVeiculoCasoNãoEstejaEstacionado() throws Exception {
    URI uri = new URI("/controle/entrada/1");

    mockMvc.perform(MockMvcRequestBuilders.post(uri).header("Authorization", token))
            .andExpect(MockMvcResultMatchers.content().json("{\"mensagem\":\"Veiculo estácionado\"}"));
  }

  @Test
  void deveriaRetirarUmVeiculoCasoEstejaEstacionado() throws Exception {
    URI uri = new URI("/controle/retirada/1");

    mockMvc.perform(MockMvcRequestBuilders.post(uri).header("Authorization", token))
            .andExpect(MockMvcResultMatchers.content().json("{\"mensagem\":\"Veiculo retirado\"}"));
  }
}