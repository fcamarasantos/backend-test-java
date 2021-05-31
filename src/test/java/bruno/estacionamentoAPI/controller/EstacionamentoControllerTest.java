package bruno.estacionamentoAPI.controller;

import bruno.estacionamentoAPI.model.Estacionamento;
import bruno.estacionamentoAPI.repository.EstacionamentoRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EstacionamentoControllerTest {

  private URI uri;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private EstacionamentoRepository estacionamentoRepository;

  @BeforeEach
  void inicializar() throws URISyntaxException {
    uri = new URI("/estacionamentos");
  }

  @Test
  @Order(1)
  void deveriaCadastrarUmEstacionamento() throws Exception {
    String body = new JSONObject()
            .put("nome", "Estacionamento teste")
            .put("email", "teste@teste.com.br")
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
  @Order(3)
  void deveriaExcluirUmEstacionamento() throws Exception {
    Optional<Estacionamento> estacionamentoDelete = estacionamentoRepository.findByEmail("teste@teste.com.br");
    URI uri = new URI("/estacionamentos/" + estacionamentoDelete.get().getId());

    mockMvc.perform(MockMvcRequestBuilders.delete(uri))
            .andExpect(MockMvcResultMatchers.status().is(204))
            .andExpect(MockMvcResultMatchers.content().json("{\"mensagem\":\"Estacionamento excluido com sucesso\"}"));
  }

  @Test
  @Order(2)
  void deveriaEditarAsInformacoesDeUmEstacionamento() throws Exception {
    Optional<Estacionamento> estacionamentoUpdate = estacionamentoRepository.findByEmail("teste@teste.com.br");
    URI uri = new URI("/estacionamentos/" + estacionamentoUpdate.get().getId());
    String body = new JSONObject()
            .put("nome", "Estacionamento atualizado")
            .put("email", "teste@teste.com.br")
            .put("senha", "1234")
            .put("cnpj", "03.775.758/0001-90")
            .put("endereco", "Rua Teste")
            .put("telefone", "11999888000")
            .put("quantidadeVagasMotos", 10)
            .put("quantidadeVagasCarros", 10).toString();

    mockMvc.perform(MockMvcRequestBuilders.put(uri).content(body).contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json("{\"nome\":\"Estacionamento atualizado\"}"));
  }

}