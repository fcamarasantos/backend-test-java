package fcamara.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
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
public class EstacionamentoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void deveriaDevolver200ListouOsEstacionamentos() throws Exception {
		URI uri = new URI("/estacionamento");
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200)
				);
	}
	
	@Test
	public void deveriaDevolver200BuscouOEstacionamento() throws Exception {
		URI uri = new URI("/estacionamento/"+"14253647586941");
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200)
				);
	}
	
	@Test
	public void deveriaDevolver201CadastrouOEstacionamento() throws Exception {
		URI uri = new URI("/estacionamento");
		String json = "{\"nome\":\"Estacionamento Céu Azul\", \"cnpj\":\"15468759231452\", \"endereco\":\"Rua de souza, 567, SP\", \"telefone\":\"11985246735\", \"qtd_moto\":25, \"qtd_carro\":50}";
	
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(201)
				);
	}
	
	@Test
	public void deveriaDevolver200AtualizouOEstacionamento() throws Exception {
		URI uri = new URI("/estacionamento/"+"14253647586941");
		String json = "{\"nome\":\"Estacionamento Céu Azul\", \"cnpj\":\"14253647586941\", \"endereco\":\"Rua de souza, 567, SP\", \"telefone\":\"11985246735\", \"qtd_moto\":25, \"qtd_carro\":50}";
	
		mockMvc
		.perform(MockMvcRequestBuilders
				.put(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200)
				);
	}
	
	@Test
	public void deveriaDevolver200DeletouOEstacionamento() throws Exception {
		URI uri = new URI("/estacionamento/"+"45678912336985");
		mockMvc
		.perform(MockMvcRequestBuilders
				.delete(uri))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200)
				);
	}
	
	

}
