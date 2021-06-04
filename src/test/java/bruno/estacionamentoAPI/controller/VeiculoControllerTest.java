package bruno.estacionamentoAPI.controller;

import bruno.estacionamentoAPI.config.security.TokenService;
import bruno.estacionamentoAPI.model.TipoVeiculo;
import bruno.estacionamentoAPI.model.Veiculo;
import bruno.estacionamentoAPI.repository.VeiculosRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VeiculoControllerTest {

  private String token;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private VeiculosRepository repository;

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
  @Order(1)
  void deveriaCadastrarUmVeiculoNoEstacionamentoTeste() throws Exception {
    URI uri = new URI("/veiculos");
    String body = new JSONObject()
            .put("marca", "Chevrolet")
            .put("modelo", "Cobalt")
            .put("cor", "Cinza")
            .put("placa", "ABC-1234")
            .put("tipo", TipoVeiculo.CARRO)
            .toString();

    mockMvc.perform(MockMvcRequestBuilders.post(uri).content(body).contentType(MediaType.APPLICATION_JSON).header("Authorization", token))
            .andExpect(MockMvcResultMatchers.status().is(201))
            .andExpect(MockMvcResultMatchers.content().json("{\"marca\":\"Chevrolet\"}"));
  }

  @Test
  @Order(2)
  void deveriaEditarAsInformacoesDoVeiculo() throws Exception {
    Optional<Veiculo> veiculo = repository.findByPlaca("ABC-1234");
    URI uri = new URI("/veiculos/" + veiculo.get().getId());
    String body = new JSONObject()
            .put("marca", "Chevrolet")
            .put("modelo", "Cobalt")
            .put("cor", "Prata")
            .put("placa", "ABC-1234")
            .put("tipo", TipoVeiculo.CARRO)
            .put("estacionamentoId", 18)
            .toString();

    mockMvc.perform(MockMvcRequestBuilders.put(uri).content(body).contentType(MediaType.APPLICATION_JSON).header("Authorization", token))
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.content().json("{\"cor\":\"Prata\"}"));
  }

  @Test
  @Order(3)
  void deveriaExcluirUmVeiculo() throws Exception {
    Optional<Veiculo> veiculoDelete = repository.findByPlaca("ABC-1234");
    URI uri = new URI("/veiculos/" + veiculoDelete.get().getId());

    mockMvc.perform(MockMvcRequestBuilders.delete(uri).header("Authorization", token))
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.content().json("{\"mensagem\":\"Veiculo excluido com sucesso\"}"));
  }

}