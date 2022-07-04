package br.com.parking.model;

public class ClientAccount extends Model{
	private String name;
	private String cpf;
	private Company comp;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Company getComp() {
		return comp;
	}
	public void setComp(Company comp) {
		this.comp = comp;
	}
}
