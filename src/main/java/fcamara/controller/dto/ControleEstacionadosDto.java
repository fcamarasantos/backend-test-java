package fcamara.controller.dto;

import java.util.ArrayList;
import java.util.List;

import fcamara.model.Controle;
import fcamara.model.Estacionamento;

public class ControleEstacionadosDto {

	private int qtd_carro;
	private int qtd_moto;
	private List<ControleDto> estacionados = new ArrayList<ControleDto>();
	
	
	public ControleEstacionadosDto(){
		
	}
	
	
	
	public int getQtd_carro() {
		return qtd_carro;
	}



	public int getQtd_moto() {
		return qtd_moto;
	}



	public List<ControleDto> getEstacionados() {
		return estacionados;
	}



	public ControleEstacionadosDto(Estacionamento estacionamento, List<Controle> estacionados) {
		this.estacionados = ControleDto.converter(estacionados);
		this.qtd_carro = estacionamento.getQtd_carro();
		this.qtd_moto = estacionamento.getQtd_moto();
	}
	
	

}
