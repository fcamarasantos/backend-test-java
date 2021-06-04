package bruno.estacionamentoAPI.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Veiculo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String marca;
  private String modelo;
  private String cor;
  private String placa;
  private TipoVeiculo tipo;
  @ManyToOne
  @JoinColumn(name = "estacionamento_id")
  private Estacionamento estacionamento;
  private boolean estacionado = false;

  public Veiculo() {

  }

  public Veiculo(String marca, String modelo, String cor, String placa, TipoVeiculo tipo) {
    this.marca = marca;
    this.modelo = modelo;
    this.cor = cor;
    this.placa = placa;
    this.tipo = tipo;
  }

  @Override
  public String toString() {
    return "Veiculo{" +
            "estacionamento=" + estacionamento.getNome()+ " " +
            "placa=" + placa +
            '}';
  }
}
