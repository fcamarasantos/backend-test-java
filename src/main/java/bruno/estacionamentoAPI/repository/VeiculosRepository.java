package bruno.estacionamentoAPI.repository;

import bruno.estacionamentoAPI.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculosRepository extends JpaRepository<Veiculo, Long> {
}
