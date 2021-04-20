package io.org.alefwhite.domain.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table( name = "pagamentos_mensalistas")
public class PagamentoMensalista {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @Column(name = "admin", columnDefinition = "false")
    private Boolean pago;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
