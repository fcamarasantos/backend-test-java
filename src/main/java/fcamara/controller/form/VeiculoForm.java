package fcamara.controller.form;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import fcamara.model.TipoVeiculo;
import fcamara.model.Veiculo;
import fcamara.repository.VeiculoRepository;

public class VeiculoForm {
	
	@NotNull @NotEmpty
	private String placa;
	
	@NotNull @NotEmpty
	private String marca;
	
	@NotNull @NotEmpty
	private String modelo;
	
	@NotNull @NotEmpty
	private String cor;
	
	@NotNull
	private TipoVeiculo tipo;
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public void setTipo(TipoVeiculo tipo) {
		this.tipo = tipo;
	}

	public Veiculo  converter() {
		return new Veiculo(placa, marca, modelo, cor, tipo);
	}
	public Veiculo atualizar(String placa, VeiculoRepository veiculoRepository) {
		Veiculo veiculo = veiculoRepository.findByPlaca(placa);
		veiculo.setPlaca(this.placa);
		veiculo.setMarca(this.marca);
		veiculo.setModelo(this.modelo);
		veiculo.setCor(this.cor);
		veiculo.setTipo(this.tipo);
		return veiculo;
	}
	
	
}
