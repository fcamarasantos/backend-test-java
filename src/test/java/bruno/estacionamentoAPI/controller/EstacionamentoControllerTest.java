package bruno.estacionamentoAPI.controller;

import bruno.estacionamentoAPI.config.security.TokenService;
import bruno.estacionamentoAPI.controller.form.LoginForm;
import bruno.estacionamentoAPI.model.Estacionamento;
import bruno.estacionamentoAPI.repository.EstacionamentoRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EstacionamentoControllerTest {

  private URI uri;

  private String token;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private EstacionamentoRepository estacionamentoRepository;

  @Autowired
  private AuthenticationManager authManager;

  @Autowired
  private TokenService tokenService;

  void login() {
    UsernamePasswordAuthenticationToken dadosLogin = new UsernamePasswordAuthenticationToken("testeEstacionaTeste@teste.com.br", "1234");
    Authentication authentication = authManager.authenticate(dadosLogin);
    token = "Bearer " + tokenService.gerarToken(authentication);
  }

  @BeforeEach
  void inicializar() throws URISyntaxException {
    uri = new URI("/estacionamentos");
  }

  @Test
  @Order(1)
  void deveriaCadastrarUmEstacionamento() throws Exception {
    String body = new JSONObject()
            .put("nome", "Estacionamento teste")
            .put("email", "testeEstacionaTeste@teste.com.br")
            .put("senha", "1234")
            .put("cnpj", "03.775.758/0001-90")
            .put("endereco", "Rua Teste")
            .put("telefone", "11999888000")
            .put("quantidadeVagasMotos", 10)
            .put("quantidadeVagasCarros", 10).toString();

    mockMvc.perform(MockMvcRequestBuilders
      .post(uri)
      .content(body)
      .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().is(201));
  }

  @Test
  @Order(2)
  void deveriaEditarAsInformacoesDeUmEstacionamento() throws Exception {
    login();
    Optional<Estacionamento> estacionamentoUpdate = estacionamentoRepository.findByEmail("testeEstacionaTeste@teste.com.br");
    URI uri = new URI("/estacionamentos");
    String body = new JSONObject()
            .put("nome", "Estacionamento atualizado")
            .put("email", "testeEstacionaTeste@teste.com.br")
            .put("senha", "1234")
            .put("cnpj", "03.775.758/0001-90")
            .put("endereco", "Rua Teste")
            .put("telefone", "11999888000")
            .put("quantidadeVagasMotos", 10)
            .put("quantidadeVagasCarros", 10).toString();

    mockMvc.perform(MockMvcRequestBuilders.put(uri).content(body).contentType(MediaType.APPLICATION_JSON).header("Authorization", token))
            .andExpect(MockMvcResultMatchers.content().json("{\"nome\":\"Estacionamento atualizado\"}"));
  }

  @Test
  @Order(3)
  void deveriaExcluirUmEstacionamento() throws Exception {
    login();
    Optional<Estacionamento> estacionamentoDelete = estacionamentoRepository.findByEmail("testeEstacionaTeste@teste.com.br");
    URI uri = new URI("/estacionamentos/" + estacionamentoDelete.get().getId());

    mockMvc.perform(MockMvcRequestBuilders.delete(uri).header("Authorization", token))
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.content().json("{\"mensagem\":\"Estacionamento excluido com sucesso\"}"));
  }

}