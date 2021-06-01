package bruno.estacionamentoAPI.controller.dto;

import bruno.estacionamentoAPI.model.Estacionamento;
import bruno.estacionamentoAPI.model.TipoVeiculo;
import bruno.estacionamentoAPI.model.Veiculo;
import lombok.Getter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class VeiculoDto {

  private Long id;
  private String marca;
  private String modelo;
  private String cor;
  private String placa;
  private TipoVeiculo tipo;
  private String estacionamento;
  private boolean estacionado;

  public VeiculoDto(Veiculo veiculo) {
    this.id = veiculo.getId();
    this.marca = veiculo.getMarca();
    this.modelo = veiculo.getModelo();
    this.cor = veiculo.getCor();
    this.placa = veiculo.getPlaca();
    this.tipo = veiculo.getTipo();
    this.estacionamento = veiculo.getEstacionamento().getNome();
    this.estacionado = veiculo.isEstacionado();
  }

  public static List<VeiculoDto> converter(List<Veiculo> veiculos) {
    return veiculos.stream().map(VeiculoDto::new).collect(Collectors.toList());
  }

  public static VeiculoDto converterUm(Veiculo veiculo) {
    return new VeiculoDto(veiculo);
  }

}
