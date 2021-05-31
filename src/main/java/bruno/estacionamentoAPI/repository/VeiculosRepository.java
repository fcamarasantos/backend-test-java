package bruno.estacionamentoAPI.repository;

import bruno.estacionamentoAPI.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VeiculosRepository extends JpaRepository<Veiculo, Long> {

  Optional<Veiculo> findByPlaca(String placa);
}
