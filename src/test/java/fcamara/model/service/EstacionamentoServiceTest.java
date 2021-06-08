package fcamara.model.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fcamara.controller.dto.EstacionamentoDto;
import fcamara.controller.form.EstacionamentoForm;
import fcamara.model.entity.Estacionamento;
import fcamara.model.repository.EstacionamentoRepository;

class EstacionamentoServiceTest {
	
	@Mock
	EstacionamentoRepository repository;
	
	private EstacionamentoService service;

	private Estacionamento estacionamento1;
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
		this.service = new EstacionamentoService(repository);
		this.estacionamento1 = new Estacionamento("Estacionamento do Juca",
				"12345678940789",
				"Rua das pintangueiras, 114, SP",
				"11984102851",
				10,
				30
				);
	}
	
	private List<Estacionamento> estacionamentos(){
		List<Estacionamento> e = new ArrayList<>();
		e.add(this.estacionamento1);
		return e;
	}
	
	@Test
	public void deveriaListarEstacionamentos() {
		List<Estacionamento> estacionamentos = estacionamentos();
		
		Mockito.when(repository.findAll())
		.thenReturn(estacionamentos);
		
		List<EstacionamentoDto> e = service.listar();
		Assert.assertNotNull(e);
	}
	
	@Test
	public void deveriaBuscarUmEstacionamento() {
		String cnpj = "12345678940789";
		Mockito.when(repository.findByCnpj(cnpj))
		.thenReturn(this.estacionamento1);
		Estacionamento estacionamento = service.buscar(cnpj);
		Assert.assertNotNull(estacionamento);
	}
	
	@Test
	public void deveriaCadastrarUmEstacionamento() {
		String msg = service.cadastrar(this.estacionamento1);
		Assert.assertEquals("Cadastrado com Sucesso!", msg);
	}
	
	@Test
	public void deveriaDeletarUmEstacionamento() {
		Mockito.when(repository.findByCnpj("12345678940789"))
		.thenReturn(this.estacionamento1);
		String msg = service.deletar("12345678940789");
		Assert.assertEquals("Estacionamento deletado com Sucesso!", msg);
	}
	
	@Test
	public void deveriaAtualizarUmEstacionamento() {
		Mockito.when(repository.findByCnpj("12345678940789"))
		.thenReturn(this.estacionamento1);
		EstacionamentoForm form = new EstacionamentoForm();
		form.setCnpj(this.estacionamento1.getCnpj());
		form.setEndereco(this.estacionamento1.getEndereco());
		form.setNome("Estacionamento Do Manoel");
		form.setQtd_carro(this.estacionamento1.getQtd_carro());
		form.setQtd_moto(this.estacionamento1.getQtd_moto());
		form.setTelefone(this.estacionamento1.getTelefone());
		String msg = service.atualizar("12345678940789", form);
		Assert.assertEquals("Estacionamento atualizado com Sucesso!", msg);
	}


}
