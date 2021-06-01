package bruno.estacionamentoAPI.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
public class Estacionamento implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String email;
  private String senha;
  private String cnpj;
  private String endereco;
  private String telefone;
  private int quantidadeVagasMotos;
  private int quantidadeVagasCarros;
  private int vagasOcupadasMotos;
  private int vagasOcupadasCarros;
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

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return this.senha;
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
