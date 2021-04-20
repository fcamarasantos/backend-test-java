package io.org.alefwhite.domain.entity;

import javax.persistence.*;

@Entity
@Table( name = "veiculos" )
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "marca", length = 100)
    private String marca;

    @Column(name = "cor", length = 100)
    private String cor;

    @Column(name = "placa", length = 7)
    private String placa;

    @Column(name = "tipo_veiculo", length = 1)
    private char tipoVeiculo;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
