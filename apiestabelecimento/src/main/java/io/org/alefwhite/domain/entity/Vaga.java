package io.org.alefwhite.domain.entity;

import javax.persistence.*;

@Entity
@Table( name = "vagas" )
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "setor", length = 1)
    private char setor;

    @Column(name = "tipo_veiculo", length = 1)
    private char tipoVeiculo;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

}