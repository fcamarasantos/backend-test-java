package fcamara.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="estacionamento")
public class Estacionamento {
	
	private String nome;
	@Id
	private String cnpj;
	private String endereco;
	private String telefone;
	private int qtd_moto;
	private int qtd_carro;
	
	public Estacionamento() {
		
	}
	
	
	public Estacionamento(String nome, String cnpj, String endereco, String telefone, int qtd_moto, int qtd_carro) {
		super();
		this.nome = nome;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.telefone = telefone;
		this.qtd_moto = qtd_moto;
		this.qtd_carro = qtd_carro;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public int getQtd_moto() {
		return qtd_moto;
	}
	public void setQtd_moto(int qtd_moto) {
		this.qtd_moto = qtd_moto;
	}
	public int getQtd_carro() {
		return qtd_carro;
	}
	public void setQtd_carro(int qtd_carro) {
		this.qtd_carro = qtd_carro;
	}
	
	
}
