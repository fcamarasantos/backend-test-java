package bruno.estacionamentoAPI.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Estacionamento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter
  private Long id;
  private String nome;
  private String email;
  private String senha;
  private String cnpj;
  private String endereco;
  private String telefone;
  private int quantidadeVagasMotos;
  private int quantidadeVagasCarros;
  @OneToMany(fetch = FetchType.EAGER, mappedBy = "estacionamento")
  private List<Veiculo> veiculos = new ArrayList();

  public Estacionamento() {

  }

  public Estacionamento(String nome, String email, String senha, String cnpj, String endereco, String telefone, int quantidadeVagasMotos, int quantidadeVagasCarros) {
    this.nome = nome;
    this.email = email;
    this.senha = senha;
    this.cnpj = cnpj;
    this.endereco = endereco;
    this.telefone = telefone;
    this.quantidadeVagasMotos = quantidadeVagasMotos;
    this.quantidadeVagasCarros = quantidadeVagasCarros;
  }
}
