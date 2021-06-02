package fcamara.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import fcamara.model.TipoVeiculo;
import fcamara.model.Veiculo;

public class VeiculoDto {
	
	private String placa;
	private String marca;
	private String modelo;
	private String cor;
	private TipoVeiculo tipo;
	
	public VeiculoDto() {
		
	}
	
	public VeiculoDto(Veiculo veiculo) {
		this.placa = veiculo.getPlaca();
		this.marca = veiculo.getMarca();
		this.modelo = veiculo.getModelo();
		this.cor = veiculo.getCor();
		this.tipo = veiculo.getTipo();
	}

	public String getMarca() {
		return marca;
	}


	public String getModelo() {
		return modelo;
	}


	public String getCor() {
		return cor;
	}


	public String getPlaca() {
		return placa;
	}


	public TipoVeiculo getTipo() {
		return tipo;
	}
	
	public static List<VeiculoDto> converter(List<Veiculo> veiculos) {
		return veiculos.stream().map(VeiculoDto::new).collect(Collectors.toList());
	}
	
	
}
