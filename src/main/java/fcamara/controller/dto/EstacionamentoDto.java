package fcamara.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Id;

import fcamara.model.Estacionamento;
import fcamara.model.TipoVeiculo;
import fcamara.model.Veiculo;

public class EstacionamentoDto {
	
	private String nome;
	private String cnpj;
	private String endereco;
	private String telefone;
	private int qtd_moto;
	private int qtd_carro;
	
	public EstacionamentoDto() {
		
	}
	
	public EstacionamentoDto(Estacionamento estacionamento) {
		this.nome = estacionamento.getNome();
		this.cnpj = estacionamento.getCnpj();
		this.endereco = estacionamento.getEndereco();
		this.telefone = estacionamento.getTelefone();
		this.qtd_moto = estacionamento.getQtd_moto();
		this.qtd_carro = estacionamento.getQtd_carro();
	}
	
	

	
	
	public String getNome() {
		return nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public int getQtd_moto() {
		return qtd_moto;
	}

	public int getQtd_carro() {
		return qtd_carro;
	}

	public static List<EstacionamentoDto> converter(List<Estacionamento> estacionamentos) {
		return estacionamentos.stream().map(EstacionamentoDto::new).collect(Collectors.toList());
	}
	
	
}
