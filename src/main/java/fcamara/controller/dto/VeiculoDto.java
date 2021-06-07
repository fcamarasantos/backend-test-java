package fcamara.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import fcamara.model.entity.TipoVeiculo;
import fcamara.model.entity.Veiculo;

public class VeiculoDto {
	
	private String msg;
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

	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
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
