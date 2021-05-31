package bruno.estacionamentoAPI.controller.dto;

import bruno.estacionamentoAPI.model.Estacionamento;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EstacionamentoDto {

  private Long id;
  private String nome;
  private String email;
  private String cnpj;
  private String endereco;
  private String telefone;
  private int quantidadeVagasMotos;
  private int quantidadeVagasCarros;

  public EstacionamentoDto(Estacionamento estacionamento) {
    this.id = estacionamento.getId();
    this.nome = estacionamento.getNome();
    this.email = estacionamento.getEmail();
    this.cnpj = estacionamento.getCnpj();
    this.endereco = estacionamento.getEndereco();
    this.telefone = estacionamento.getTelefone();
    this.quantidadeVagasMotos = estacionamento.getQuantidadeVagasMotos();
    this.quantidadeVagasCarros = estacionamento.getQuantidadeVagasCarros();
  }

  public static List<EstacionamentoDto> converter(List<Estacionamento> estacionamentos) {
    return estacionamentos.stream().map(EstacionamentoDto::new).collect(Collectors.toList());
  }

  public static EstacionamentoDto converterUm(Estacionamento estacionamento) {
    return new EstacionamentoDto(estacionamento);
  }
}
