package fcamara.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import fcamara.model.entity.Controle;
import fcamara.model.entity.Estacionamento;
import fcamara.model.entity.TipoVeiculo;
import fcamara.model.entity.Veiculo;

public class ControleDto {
	
	private String veiculo;
	private String estacionamento;
	private LocalDateTime datahora_entrada;
	private LocalDateTime datahora_saida;
	
	public ControleDto() {
		
	}
	
	public ControleDto(Controle controle) {
		this.veiculo = controle.getVeiculo().getMarca() + " " + controle.getVeiculo().getModelo() +" " +controle.getVeiculo().getPlaca();
		this.estacionamento = controle.getEstacionamento().getNome();
		this.datahora_entrada = controle.getDatahora_entrada();
		this.datahora_saida = controle.getDatahora_saida();
	}
	

	public String getVeiculo() {
		return veiculo;
	}

	public String getEstacionamento() {
		return estacionamento;
	}

	public LocalDateTime getDatahora_entrada() {
		return datahora_entrada;
	}

	public LocalDateTime getDatahora_saida() {
		return datahora_saida;
	}

	public static List<ControleDto> converter(List<Controle> lista) {
		return lista.stream().map(ControleDto::new).collect(Collectors.toList());
	}
	
	
}
