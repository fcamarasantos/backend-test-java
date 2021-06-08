package fcamara.model.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fcamara.controller.dto.ControleDto;
import fcamara.controller.dto.ControleEstacionadosDto;
import fcamara.controller.form.ControleForm;
import fcamara.model.entity.Controle;
import fcamara.model.entity.Estacionamento;
import fcamara.model.entity.TipoVeiculo;
import fcamara.model.entity.Veiculo;
import fcamara.model.repository.ControleRepository;
import fcamara.model.repository.EstacionamentoRepository;
import fcamara.model.repository.VeiculoRepository;

class ControleServiceTest {
	@Mock
	VeiculoRepository veiculoRepository;
	@Mock
	EstacionamentoRepository estacionamentoRepository;
	@Mock
	ControleRepository controleRepository;
	
	private ControleService service;

	private Estacionamento estacionamento;
	private Veiculo veiculo;
	private Controle controle;
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
		this.service = new ControleService(controleRepository,estacionamentoRepository,veiculoRepository);
		this.estacionamento = new Estacionamento("Estacionamento do Juca",
				"12345678940789",
				"Rua das pintangueiras, 114, SP",
				"11984102851",
				10,
				30
				);
		this.veiculo = new Veiculo("VOLKSWAGEN",
				"GOLF GTI",
				"PRETO",
				"ABC1D231",
				TipoVeiculo.CARRO
				);
		this.controle = new Controle(veiculo, estacionamento);
	}
	
	private List<Controle> controles(){
		List<Controle> c = new ArrayList<>();
		c.add(this.controle);
		this.controle.setDatahora_saida(LocalDateTime.now());
		c.add(this.controle);
		return c;
	}
	
	
	@Test
	public void deveriaListarControles() {
		List<Controle> controles = controles();
		
		Mockito.when(controleRepository.findAll())
		.thenReturn(controles);
		
		List<ControleDto> c = service.listar();
		Assert.assertNotNull(c);
	}
	
	@Test
	public void deveriaBuscarListaDeControleDeEstacionadosDeUmEstacionamento() {
		List<Controle> controles = controles();
		String cnpj = this.estacionamento.getCnpj();
		Mockito.when(estacionamentoRepository.findByCnpj(cnpj))
		.thenReturn(this.estacionamento);
		Mockito.when(controleRepository.findByCnpjAndDatahoraSaidaNull(cnpj))
		.thenReturn(controles);
		ControleEstacionadosDto controle = service.buscarEstacionados(cnpj);
		Assert.assertNotNull(controle);
	}
	
	@Test
	public void deveriaBuscarListaDeControleDeHistoricoDeUmEstacionamento() {
		List<Controle> controles = controles();
		String cnpj = this.estacionamento.getCnpj();
		Mockito.when(estacionamentoRepository.findByCnpj(cnpj))
		.thenReturn(this.estacionamento);
		Mockito.when(controleRepository.findByCnpjAndDatahoraSaidaNotNull(cnpj))
		.thenReturn(controles);
		ControleEstacionadosDto controle = service.buscarHistorico(cnpj);
		Assert.assertNotNull(controle);
	}
	
	
	

}
