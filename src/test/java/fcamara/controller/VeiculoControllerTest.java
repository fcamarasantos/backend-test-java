package fcamara.controller;

import java.net.URI;

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
public class VeiculoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private static String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgZG8gYmFja2VuZHRlc3QiLCJzdWIiOiIxIiwiaWF0IjoxNjIzMTU5MTM5LCJleHAiOjE2MjMyNDU1Mzl9.jPESLwCV3DLvZcoadqPUdZJQT9K0amIprUN1gVOfogk";

	
	@Test
	public void deveriaDevolver200ListouOsVeiculos() throws Exception {
		URI uri = new URI("/veiculo");
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri)
				.header("authorization", "Bearer " + token))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200)
				);
	}
	@Test
	public void naoDeveriaDevolver200EListarOsVeiculosPoisNaoEstaAutenticado() throws Exception {
		URI uri = new URI("/veiculo");
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(403)
				);
	}
	
	@Test
	public void deveriaDevolver200BuscouOVeiculo() throws Exception {
		URI uri = new URI("/veiculo/"+"FGH5J231");
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri)
				.header("authorization", "Bearer " + token))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200)
				);
	}
	
	@Test
	public void naoDeveriaDevolver200EBuscarOVeiculoPoisNaoEstaAutenticado() throws Exception {
		URI uri = new URI("/veiculo/"+"FGH5J231");
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(403)
				);
	}
	
	@Test
	public void deveriaDevolver400NaoAchouOVeiculo() throws Exception {
		URI uri = new URI("/veiculo/"+"AAA0A000");
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri)
				.header("authorization", "Bearer " + token))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(404)
				);
	}
	
	@Test
	public void deveriaDevolver201CadastrouOVeiculo() throws Exception {
		URI uri = new URI("/veiculo");
		String json = "{\"placa\":\"IOP5L678\", \"marca\":\"MERCEDES\", \"modelo\":\"S63\", \"cor\":\"CHUMBO\", \"tipo\":\"CARRO\"}";
	
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.header("authorization", "Bearer " + token))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(201)
				);
	}
	
	@Test
	public void naoDeveriaDevolver201CadastrouOVeiculoPoisNaoEstaAutenticado() throws Exception {
		URI uri = new URI("/veiculo");
		String json = "{\"placa\":\"IOP5L678\", \"marca\":\"MERCEDES\", \"modelo\":\"S63\", \"cor\":\"CHUMBO\", \"tipo\":\"CARRO\"}";
	
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(403)
				);
	}
	
	@Test
	public void deveriaDevolver400PoisOVeiculoJaEstaCadastrado() throws Exception {
		URI uri = new URI("/veiculo");
		String json = "{\"placa\":\"ABC1D231\", \"marca\":\"MERCEDES\", \"modelo\":\"S63\", \"cor\":\"CHUMBO\", \"tipo\":\"CARRO\"}";
	
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.header("authorization", "Bearer " + token))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400)
				);
	}
	
	@Test
	public void deveriaDevolver200AtualizouOVeiculo() throws Exception {
		URI uri = new URI("/veiculo/"+"FGH5J231");
		String json = "{\"placa\":\"FGH5J231\", \"marca\":\"VOLKSWAGEN\", \"modelo\":\"JETTA TURBO\", \"cor\":\"PRETO\", \"tipo\":\"CARRO\"}";
	
		mockMvc
		.perform(MockMvcRequestBuilders
				.put(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.header("authorization", "Bearer " + token))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200)
				);
	}
	
	@Test
	public void naoDeveriaDevolver200AtualizouOVeiculoPoisNaoEstaAutenticado() throws Exception {
		URI uri = new URI("/veiculo/"+"FGH5J231");
		String json = "{\"placa\":\"FGH5J231\", \"marca\":\"VOLKSWAGEN\", \"modelo\":\"JETTA TURBO\", \"cor\":\"PRETO\", \"tipo\":\"CARRO\"}";
	
		mockMvc
		.perform(MockMvcRequestBuilders
				.put(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(403)
				);
	}
	
	@Test
	public void deveriaDevolver400NaoAtualizouOVeiculoPoisNaoOEncontrou() throws Exception {
		URI uri = new URI("/veiculo/"+"AAA0A000");
		String json = "{\"placa\":\"FGH5J231\", \"marca\":\"VOLKSWAGEN\", \"modelo\":\"JETTA TURBO\", \"cor\":\"PRETO\", \"tipo\":\"CARRO\"}";
	
		mockMvc
		.perform(MockMvcRequestBuilders
				.put(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.header("authorization", "Bearer " + token))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400)
				);
	}
	
	@Test
	public void deveriaDevolver200DeletouOVeiculo() throws Exception {
		URI uri = new URI("/veiculo/"+"FGH5J231");
		mockMvc
		.perform(MockMvcRequestBuilders
				.delete(uri)
				.header("authorization", "Bearer " + token))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200)
				);
	}
	
	@Test
	public void naoDeveriaDevolver200DeletouOVeiculoPoisNaoEstaAutenticado() throws Exception {
		URI uri = new URI("/veiculo/"+"FGH5J231");
		mockMvc
		.perform(MockMvcRequestBuilders
				.delete(uri))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(403)
				);
	}
	
	@Test
	public void deveriaDevolver400NaoDeletouOVeiculoPoisNaoOEncontrou() throws Exception {
		URI uri = new URI("/veiculo/"+"AAA0A000");
		mockMvc
		.perform(MockMvcRequestBuilders
				.delete(uri)
				.header("authorization", "Bearer " + token))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400)
				);
	}
	
	

}
