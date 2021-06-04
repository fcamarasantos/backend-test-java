package fcamara.controller.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import fcamara.model.Controle;
import fcamara.model.Estacionamento;
import fcamara.model.Veiculo;
import fcamara.repository.ControleRepository;
import fcamara.repository.EstacionamentoRepository;
import fcamara.repository.VeiculoRepository;

public class ControleForm {
	@NotNull @NotEmpty
	private String placa;
	@NotNull @NotEmpty
	private String cnpj;
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public Controle converter(VeiculoRepository veiculoRepository, EstacionamentoRepository estacionamentoRepository) {
		Veiculo veiculo = veiculoRepository.findByPlaca(this.placa);
		Estacionamento estacionamento = estacionamentoRepository.findByCnpj(this.cnpj);
		return new Controle(veiculo, estacionamento);
	}
	
	public Controle atualizar(ControleRepository controleRepository) {
		Controle controle = controleRepository.findByPlacaAndDatahoraSaidaNull(this.placa);
		controle.setDatahora_saida(LocalDateTime.now());
		return controle; 
	}
	
}
