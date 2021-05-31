package bruno.estacionamentoAPI.repository;

import bruno.estacionamentoAPI.model.Estacionamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long> {
}
