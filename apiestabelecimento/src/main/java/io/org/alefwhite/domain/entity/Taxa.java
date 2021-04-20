package io.org.alefwhite.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table( name = "taxas")
public class Taxa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column( name = "descricao")
    private String descricao;

    @Column( name = "tempo")
    private Integer tempo;

    @Column( name = "valor", precision = 20, scale = 2)
    private BigDecimal valor;
}
