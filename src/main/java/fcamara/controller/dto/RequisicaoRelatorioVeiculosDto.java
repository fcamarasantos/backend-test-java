package fcamara.controller.dto;

import fcamara.model.entity.Veiculo;

public class RequisicaoRelatorioVeiculosDto {
	
	private String PLACA;
	private String MARCA;
	private String MODELO;
	private String COR;
	private String TIPO;
	
	public RequisicaoRelatorioVeiculosDto() {
		
	}
	
	public RequisicaoRelatorioVeiculosDto(Veiculo veiculo) {
		this.PLACA = veiculo.getPlaca();
		this.MARCA = veiculo.getMarca();
		this.MODELO = veiculo.getModelo();
		this.COR = veiculo.getCor();
		this.TIPO = veiculo.getTipo().toString();
	}

	public String getPLACA() {
		return PLACA;
	}


	public String getMARCA() {
		return MARCA;
	}


	public String getMODELO() {
		return MODELO;
	}


	public String getCOR() {
		return COR;
	}


	public String getTIPO() {
		return TIPO;
	}


	
}
