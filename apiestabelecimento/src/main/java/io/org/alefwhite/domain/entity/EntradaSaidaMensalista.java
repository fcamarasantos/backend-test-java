package io.org.alefwhite.domain.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "entrada_saida_mensalistas")
public class EntradaSaidaMensalista {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "vaga_id")
    private Vaga vaga;

    @Column(name = "data_entrada")
    private LocalDate dataEntrada = LocalDate.now();

    @Column(name = "data_saida", columnDefinition = "null")
    private LocalDate dataSaida;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

}
