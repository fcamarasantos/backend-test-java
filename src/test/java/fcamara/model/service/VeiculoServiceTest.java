package fcamara.model.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fcamara.controller.dto.VeiculoDto;
import fcamara.controller.form.VeiculoForm;
import fcamara.model.entity.TipoVeiculo;
import fcamara.model.entity.Veiculo;
import fcamara.model.repository.VeiculoRepository;

public class VeiculoServiceTest {
	
	@Mock
	VeiculoRepository veiculoRepository;
	
	private VeiculoService service;
	
	private Veiculo veiculo1;
	private Veiculo veiculo2;
	
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
		this.service = new VeiculoService(veiculoRepository);
		this.veiculo2 = new Veiculo("VOLKSWAGEN",
				"GOLF GTI",
				"PRETO",
				"ABC1D231",
				TipoVeiculo.CARRO
				);
		this.veiculo1 = new Veiculo("NISSAN",
				"GTR R35",
				"BRANCO",
				"GTR0A000",
				TipoVeiculo.CARRO
				);
	}
	
	private List<Veiculo> veiculos(){
		List<Veiculo> v = new ArrayList<>();
		v.add(this.veiculo1);
		v.add(this.veiculo2);
		return v;
	}
	
	@Test
	public void deveriaListarVeiculos() {
		List<Veiculo> veiculos = veiculos();
		
		Mockito.when(veiculoRepository.findAll())
		.thenReturn(veiculos);
		
		List<VeiculoDto> v = service.listar();
		Assert.assertNotNull(v);
	}
	
	@Test
	public void deveriaBuscarUmVeiculo() {
		String placa = "ABC1D231";
		Mockito.when(veiculoRepository.findByPlaca(placa))
		.thenReturn(this.veiculo2);
		Veiculo veiculo = service.buscar(placa);
		Assert.assertNotNull(veiculo);
	}
	
	@Test
	public void deveriaCadastrarUmVeiculo() {
		String msg = service.cadastrar(this.veiculo1);
		Assert.assertEquals("Cadastrado com Sucesso!", msg);
	}
	
	@Test
	public void deveriaDeletarUmVeiculo() {
		Mockito.when(veiculoRepository.findByPlaca("ABC1D231"))
		.thenReturn(this.veiculo2);
		String msg = service.deletar("ABC1D231");
		Assert.assertEquals("Veiculo deletado com Sucesso!", msg);
	}
	
	@Test
	public void deveriaAtualizarUmVeiculo() {
		Mockito.when(veiculoRepository.findByPlaca("ABC1D231"))
		.thenReturn(this.veiculo2);
		VeiculoForm form = new VeiculoForm();
		form.setCor(this.veiculo2.getCor());
		form.setMarca(this.veiculo2.getMarca());
		form.setModelo("GOLF SPORTLINE");
		form.setPlaca(this.veiculo2.getPlaca());
		form.setTipo(this.veiculo2.getTipo());
		String msg = service.atualizar("ABC1D231", form);
		Assert.assertEquals("Veiculo atualizado com Sucesso!", msg);
	}

}
