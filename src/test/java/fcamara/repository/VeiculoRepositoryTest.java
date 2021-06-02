package fcamara.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import fcamara.model.TipoVeiculo;
import fcamara.model.Veiculo;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VeiculoRepositoryTest {
	
	@Autowired
	private VeiculoRepository repository;
	
	@Autowired
	private TestEntityManager em;
	

	@Test
	public void deveriaSalvarUmVeiculo() {
		Veiculo veiculo = new Veiculo("VOLKSWAGEN",
				"GOLF GTI",
				"PRETO",
				"ABC1D231",
				TipoVeiculo.CARRO
				);
		long id = (long)em.persistAndGetId(veiculo);
		Veiculo persistiu = repository.findById(id).get();
		
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
		
		long id = (long)em.persistAndGetId(veiculo);
		
		veiculo.setModelo("GOLF TSI");
		repository.save(veiculo);
		Veiculo alterou = repository.findById(id).get();
		
		Assert.assertEquals("GOLF TSI",alterou.getModelo());
	}
	
	@Test //PRECISA ARRUMAR!!
	public void deveriaApagarUmVeiculo() {
		Veiculo veiculo = new Veiculo("VOLKSWAGEN",
				"GOLF GTI",
				"PRETO",
				"ABC1D231",
				TipoVeiculo.CARRO
				);
		
		long id = (long)em.persistAndGetId(veiculo);
		//em.remove(deletou);
		
		repository.deleteById(id);
		Veiculo deletou = repository.findById(1l).get();
		Assert.assertNull(deletou);
	}
	
	@Test
	public void deveriaListarTodosOsVeiculos() {
		List<Veiculo> veiculos = repository.findAll();
		Assert.assertNotNull(veiculos);
	}

}
