package com.fcamarasantos.testebackendjava.domain.registroEntradaSaida;

import com.fcamarasantos.testebackendjava.domain.estabelecimento.Estabelecimento;
import com.fcamarasantos.testebackendjava.domain.veiculo.Veiculo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "registros_entrada_saida")
@Entity(name = "RegistroEntradaSaida")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroEntradaSaida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estabelecimento_id")
    public Estabelecimento estabelecimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veiculo_id")
    public Veiculo veiculo;

    @Column(name = "data_entrada")
    public LocalDateTime dataEntrada;

    @Column(name = "data_saida")
    public LocalDateTime dataSaida;

}
