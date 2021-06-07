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

import fcamara.model.entity.Estacionamento;
import fcamara.model.entity.TipoVeiculo;
import fcamara.model.entity.Veiculo;
import fcamara.model.repository.EstacionamentoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EstacionamentoRepositoryTest {
	
	@Autowired
	private EstacionamentoRepository repository;
	
	@Autowired
	private TestEntityManager em;
	

	@Test
	public void deveriaSalvarUmEstacionamento() {
		Estacionamento estacionamento = new Estacionamento("Estacionamento do Juca",
				"12345678940789",
				"Rua das pintangueiras, 114, SP",
				"11984102851",
				10,
				30
				);
		String cnpj = (String)em.persistAndGetId(estacionamento);
		Estacionamento persistiu = repository.findByCnpj(cnpj);
		
		Assert.assertNotNull(persistiu);
		Assert.assertEquals(estacionamento.getCnpj(), persistiu.getCnpj());
	}
	
	@Test
	public void deveriaAlterarUmVeiculo() {
		Estacionamento estacionamento = new Estacionamento("Estacionamento do Juca",
				"12345678940789",
				"Rua das pintangueiras, 114, SP",
				"11984102851",
				10,
				30
				);
		
		String cnpj = (String)em.persistAndGetId(estacionamento);
		
		estacionamento.setTelefone("11984106598");
		repository.save(estacionamento);
		Estacionamento alterou = repository.findByCnpj(cnpj);
		
		Assert.assertEquals("11984106598",alterou.getTelefone());
	}
	
	@Test //PRECISA ARRUMAR!!
	public void deveriaApagarUmVeiculo() {
		Estacionamento estacionamento = new Estacionamento("Estacionamento do Juca",
				"12345678940789",
				"Rua das pintangueiras, 114, SP",
				"11984102851",
				10,
				30
				);
		
		String cnpj = (String)em.persistAndGetId(estacionamento);
		//em.remove(deletou);
		
		repository.deleteById(cnpj);
		Estacionamento deletou = repository.findByCnpj("12345678940789");
		Assert.assertNull(deletou);
	}
	
	@Test
	public void deveriaListarTodosOsVeiculos() {
		List<Estacionamento> estacionamentos = repository.findAll();
		Assert.assertNotNull(estacionamentos);
	}

}
