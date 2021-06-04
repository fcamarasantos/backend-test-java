package fcamara.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import fcamara.model.Controle;
import fcamara.model.Estacionamento;
import fcamara.model.TipoVeiculo;
import fcamara.model.Veiculo;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ControleRepositoryTest {

	@Autowired
	private ControleRepository repository;
	
	@Autowired
	private TestEntityManager em;
	
	@Test
	public void deveriaSalvarAEntradaDeUmVeiculoNoEstacionamento() {
		Estacionamento estacionamento = new Estacionamento("Estacionamento do Juca",
				"12345678940789",
				"Rua das pintangueiras, 114, SP",
				"11984102851",
				10,
				30
				);
		Veiculo veiculo = new Veiculo("VOLKSWAGEN",
				"GOLF GTI",
				"PRETO",
				"ABC1D231",
				TipoVeiculo.CARRO
				);
		Controle controle = new Controle(veiculo, estacionamento);
		
		Long id = (Long) em.persistAndGetId(controle);
		Controle persistiu = repository.findById(id).get();
		
		Assert.assertNotNull(persistiu);
		Assert.assertEquals(controle.getEstacionamento().getCnpj(), persistiu.getEstacionamento().getCnpj());
		Assert.assertEquals(controle.getVeiculo().getPlaca(), persistiu.getVeiculo().getPlaca());
	}
	
	@Test
	public void deveriaLocalizarUsandoTodasAsQuerys() {
		Assert.assertNotNull(repository.findByCnpjAndDatahoraSaidaNull("14253647586941"));
		Assert.assertNotNull(repository.findByCnpjAndPlacaAndDatahoraSaidaNull("14253647586941", "ABC1D231"));
		Assert.assertNotNull(repository.findByPlacaAndDatahoraSaidaNull("ABC1D231"));
		Assert.assertEquals(new ArrayList<Controle>(),repository.findByCnpjAndDatahoraSaidaNotNull("14253647586941"));
	}
	
	@Test
	public void deveriaListarTodosOsRegistrosDeControle() {
		List<Controle> controles= repository.findAll();
		Assert.assertNotNull(controles);
	}

}
