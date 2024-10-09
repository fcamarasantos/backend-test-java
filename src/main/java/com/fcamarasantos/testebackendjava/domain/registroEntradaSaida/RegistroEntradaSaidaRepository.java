package com.fcamarasantos.testebackendjava.domain.registroEntradaSaida;

import com.fcamarasantos.testebackendjava.domain.estabelecimento.Estabelecimento;
import com.fcamarasantos.testebackendjava.domain.veiculo.TipoVeiculo;
import com.fcamarasantos.testebackendjava.domain.veiculo.Veiculo;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegistroEntradaSaidaRepository extends JpaRepository<RegistroEntradaSaida, Long> {

    @Query("""
                       SELECT r FROM
                         RegistroEntradaSaida r
                       WHERE 
                        r.estabelecimento.id = :idEstabelecimento AND
                    r.veiculo.id = :idVeiculo AND
                    r.dataSaida is NULL
            """)
    RegistroEntradaSaida buscarRegistroRelacionado(@NotNull Long idEstabelecimento, @NotNull Long idVeiculo);

    @Query("""
        select count(r) from
            RegistroEntradaSaida r
        where
            r.estabelecimento.id = :idEstabelecimento and
            r.veiculo.tipo = :tipoVeiculo and
            r.dataSaida IS NULL
    """)
    Integer getVagasDisponiveis(@NotNull Long idEstabelecimento, TipoVeiculo tipoVeiculo);

    Boolean existsByVeiculoIdAndDataSaidaIsNull(@NotNull Long idVeiculo);
}
