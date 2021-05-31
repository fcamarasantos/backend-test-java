package bruno.estacionamentoAPI.controller.form;

import bruno.estacionamentoAPI.model.Estacionamento;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EstacionamentoForm {

  @NotNull
  private String nome;
  @NotNull
  private String email;
  @NotNull @Length(min = 4, max = 20)
  private String senha;
  @NotNull @Length(min = 14, max = 14)
  private String cnpj;
  @NotNull
  private String endereco;
  @NotNull @Length(min = 11)
  private String telefone;
  @NotNull
  private int quantidadeVagasMotos;
  @NotNull
  private int quantidadeVagasCarros;

  public Estacionamento converter() {
    return new Estacionamento(nome, email, senha, cnpj, endereco, telefone, quantidadeVagasMotos, quantidadeVagasCarros);
  }
}
