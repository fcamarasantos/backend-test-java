package io.org.alefwhite.domain.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table( name = "empresas" )
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "endereco", length = 100)
    private String endereco;

    @Column(name = "cnpj", length = 14, unique = true)
    private String cnpj;

    @Column(name = "telfone", length = 15)
    private String telfone;

    @Column(name = "qtd_vagas_motos")
    private Integer QtdagasMotos;

    @Column(name = "qtd_vagas_carros")
    private Integer QtdVagasCarros;

    @OneToMany( mappedBy = "empresa" , fetch = FetchType.LAZY )
    private Set<Usuario> usuarios;

    @OneToMany( mappedBy = "empresa" , fetch = FetchType.LAZY )
    private Set<Vaga> vagas;

}