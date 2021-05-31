package bruno.estacionamentoAPI.model;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Estacionamento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  private String nome;
  @NotNull
  private String email;
  @NotNull
  private String senha;
  @NotNull
  private String cnpj;
  @NotNull
  private String endereco;
  @NotNull
  private String telefone;
  @NotNull
  private int quantidadeVagasMotos;
  @NotNull
  private int quantidadeVagasCarros;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "estacionamento")
  private List<Veiculo> veiculos = new ArrayList();

}
