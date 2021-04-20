package io.org.alefwhite.domain.entity;

import io.org.alefwhite.domain.enums.DiaVencimentoMensalista;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table( name = "clientes" )
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @Column(name = "telefone", length = 15)
    private String telefone;

    @Column(name = "mensalista", columnDefinition = "false")
    private Boolean mensalista;

    @Column(name = "dia_vencimento_mensalista", length = 2)
    private Integer diaVencimentoMensalista;

    @OneToMany( mappedBy = "cliente" , fetch = FetchType.LAZY )
    private Set<Veiculo> veiculos;

}
