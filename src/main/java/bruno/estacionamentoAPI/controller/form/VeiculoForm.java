package bruno.estacionamentoAPI.controller.form;

import bruno.estacionamentoAPI.model.TipoVeiculo;
import bruno.estacionamentoAPI.model.Veiculo;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VeiculoForm {

  @NotNull
  private String marca;
  @NotNull
  private String modelo;
  @NotNull
  private String cor;
  @NotNull @Length(min = 7, max = 8)
  private String placa;
  @NotNull
  private TipoVeiculo tipo;

  public Veiculo converter() {
    return new Veiculo(marca, modelo, cor, placa, tipo);
  }

}
