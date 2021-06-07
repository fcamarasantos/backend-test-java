package fcamara.controller.form;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import fcamara.model.entity.Estacionamento;
import fcamara.model.repository.EstacionamentoRepository;

public class EstacionamentoForm {
	
	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
	private String cnpj;
	@NotNull @NotEmpty
	private String endereco;
	@NotNull @NotEmpty
	private String telefone;
	
	@NotNull
	private int qtd_moto;
	@NotNull
	private int qtd_carro;
	
	
	

	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public void setQtd_moto(int qtd_moto) {
		this.qtd_moto = qtd_moto;
	}
	public void setQtd_carro(int qtd_carro) {
		this.qtd_carro = qtd_carro;
	}
	
	public Estacionamento  converter() {
		return new Estacionamento(nome, cnpj, endereco, telefone, qtd_moto, qtd_carro);
	}
	
	public Estacionamento atualizar(String cnpj, EstacionamentoRepository estacionamentoRepository) {
		Estacionamento estacionamento = estacionamentoRepository.findByCnpj(cnpj);
		estacionamento.setCnpj(this.cnpj);
		estacionamento.setNome(this.nome);
		estacionamento.setEndereco(this.endereco);
		estacionamento.setTelefone(this.telefone);
		estacionamento.setQtd_moto(this.qtd_moto);
		estacionamento.setQtd_carro(this.qtd_carro);
		return estacionamento;
	}
	
	
}
