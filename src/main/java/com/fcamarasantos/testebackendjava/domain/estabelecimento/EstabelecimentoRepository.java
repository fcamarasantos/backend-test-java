package com.fcamarasantos.testebackendjava.domain.estabelecimento;
import com.fcamarasantos.testebackendjava.domain.registroEntradaSaida.RegistroEntradaSaida;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {

}
