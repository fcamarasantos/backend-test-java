package fcamara.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import fcamara.model.entity.TipoVeiculo;
import fcamara.model.entity.Veiculo;
import fcamara.model.repository.VeiculoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VeiculoRepositoryTest {
	
	@Autowired
	private VeiculoRepository repository;
	
	@Autowired
	private TestEntityManager em;
	
	@Test
	public void deveriaCarregarUmVeiculoAoBuscarPelaSuaPlaca() {
		Veiculo veiculo = new Veiculo("KAWASAKI",
				"H2R",
				"CARBONO",
				"UJZ8S258",
				TipoVeiculo.MOTO
				);
		em.persist(veiculo);
		Veiculo carregou = repository.findByPlaca(veiculo.getPlaca());
		Assert.assertNotNull(carregou);
		Assert.assertEquals(veiculo.getPlaca(), carregou.getPlaca());
	}
	
	@Test
	public void naoDeveriaCarregarUmVeiculoQueNaoEstaCadastrado() {
		String placa = "AAA0A000";
		Veiculo veiculo = repository.findByPlaca(placa);
		Assert.assertNull(veiculo);
	}

	@Test
	public void deveriaSalvarUmVeiculo() {
		Veiculo veiculo = new Veiculo("NISSAN",
				"GTR R35",
				"BRANCO",
				"GTR0A000",
				TipoVeiculo.CARRO
				);
		String placa = (String) em.persistAndGetId(veiculo);
		Veiculo persistiu = repository.findByPlaca(placa);
		
		Assert.assertNotNull(persistiu);
		Assert.assertEquals(veiculo.getPlaca(), persistiu.getPlaca());
	}
	
	@Test
	public void deveriaAlterarUmVeiculo() {
		Veiculo veiculo = new Veiculo("VOLKSWAGEN",
				"GOLF GTI",
				"PRETO",
				"ABC1D231",
				TipoVeiculo.CARRO
				);
		
		String placa = (String) em.persistAndGetId(veiculo);
		
		veiculo.setModelo("GOLF TSI");
		repository.save(veiculo);
		Veiculo alterou = repository.findByPlaca(placa);
		
		Assert.assertEquals("GOLF TSI",alterou.getModelo());
	}
	
	
	@Test
	public void deveriaApagarUmVeiculo() {
		Veiculo veiculo = new Veiculo("VOLKSWAGEN",
				"GOLF GTI",
				"PRETO",
				"ABC1D231",
				TipoVeiculo.CARRO
				);
		
		String placa = (String) em.persistAndGetId(veiculo);
		repository.deleteById(placa);
		Veiculo deletou = repository.findByPlaca(placa);
		Assert.assertNull(deletou);
	}
	
	
	@Test
	public void deveriaListarTodosOsVeiculos() {
		List<Veiculo> veiculos = repository.findAll();
		Assert.assertNotNull(veiculos);
	}

}
