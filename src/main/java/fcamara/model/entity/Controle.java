package fcamara.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="controle")
public class Controle {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Veiculo veiculo;
	
	@ManyToOne
	private Estacionamento estacionamento;
	
	private LocalDateTime datahora_entrada = LocalDateTime.now();
	private LocalDateTime datahora_saida = null;
	
	public Controle() {
	}
	
	public Controle(Controle controle) {
		this.veiculo = controle.getVeiculo();
		this.estacionamento = controle.getEstacionamento();
		this.datahora_entrada = controle.getDatahora_entrada();
		this.datahora_saida = controle.getDatahora_saida();
	}

	public Controle(Veiculo veiculo, Estacionamento estacionamento) {
		this.veiculo = veiculo;
		this.estacionamento = estacionamento;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Estacionamento getEstacionamento() {
		return estacionamento;
	}

	public void setEstacionamento(Estacionamento estacionamento) {
		this.estacionamento = estacionamento;
	}

	public LocalDateTime getDatahora_entrada() {
		return datahora_entrada;
	}

	public void setDatahora_entrada(LocalDateTime datahora_entrada) {
		this.datahora_entrada = datahora_entrada;
	}

	public LocalDateTime getDatahora_saida() {
		return datahora_saida;
	}

	public void setDatahora_saida(LocalDateTime datahora_saida) {
		this.datahora_saida = datahora_saida;
	}

	
}
