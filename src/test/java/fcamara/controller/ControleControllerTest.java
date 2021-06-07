package fcamara.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class ControleControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	public void deveriaDarEntradaNoEstacionamento() throws Exception {
		URI uri = new URI("/controle");
		String json = "{\"cnpj\":\"14253647586941\", \"placa\":\"FGH5J231\"}";
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200)
				);
	}
	
	@Test
	public void naoDeveriaDarEntradaNoEstacionamentoPoisNaoHaVagasDisponiveis() throws Exception {
		URI uri = new URI("/controle");
		String msg = "Não há vagas disponíveis no momento!";
		String json = "{\"cnpj\":\"75123455245625\", \"placa\":\"KIL1D331\"}";
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400)
				)
		.andExpect(MockMvcResultMatchers
				.content()
				.string("{\"msg\":\""+msg+"\"}"));
	}
	
	@Test
	public void aoTentarEstacionarNaoLocalizarCnpj() throws Exception {
		URI uri = new URI("/controle");
		String msg = "Cnpj do estacionamento não encontrado!";
		String json = "{\"cnpj\":\"erro\", \"placa\":\"FGH5J231\"}";
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(404)
				)
		.andExpect(MockMvcResultMatchers
				.content()
				.string("{\"msg\":\""+msg+"\"}"));
	}
	
	@Test
	public void aoTentarEstacionarNaoLocalizarPlaca() throws Exception {
		URI uri = new URI("/controle");
		String msg = "Placa do veiculo não encontrado!";
		String json = "{\"cnpj\":\"75123455245625\", \"placa\":\"erro\"}";
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(404)
				)
		.andExpect(MockMvcResultMatchers
				.content()
				.string("{\"msg\":\""+msg+"\"}"));
	}
	
	@Test
	public void deveriaSairDoEstacionamento() throws Exception {
		URI uri = new URI("/controle");
		String msg = "Volte Sempre!";
		String json = "{\"cnpj\":\"14253647586941\", \"placa\":\"ABC1D231\"}";
		mockMvc
		.perform(MockMvcRequestBuilders
				.put(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200)
				)
		.andExpect(MockMvcResultMatchers
				.content()
				.string("{\"msg\":\""+msg+"\"}"));
	}
	
	
	@Test
	public void deveriaListarVeiculosEstacionados() throws Exception {
		URI uri = new URI("/controle/14253647586941/estacionados");
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200)
				);
	}
	@Test
	public void naoDeveriaListarVeiculosEstacionados() throws Exception {
		URI uri = new URI("/controle/erro/estacionados");
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(404)
				);
	}
	
	@Test
	public void deveriaListarHistoricoDeVeiculosEstacionados() throws Exception {
		URI uri = new URI("/controle/14253647586941/historico");
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200)
				);
	}
	
	@Test
	public void naoDeveriaListarHistoricoDeVeiculosEstacionados() throws Exception {
		URI uri = new URI("/controle/erro/historico");
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(404)
				);
	}
	

}
