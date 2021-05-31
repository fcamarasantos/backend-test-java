package bruno.estacionamentoAPI.model;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
public class Veiculo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  private String marca;
  @NotNull
  private String modelo;
  @NotNull
  private String cor;
  @NotNull
  private String placa;
  @NotNull
  private TipoVeiculo tipo;
  @NotNull
  @ManyToOne
  @JoinColumn(name = "estacionamento_id")
  private Estacionamento estacionamento;

}
