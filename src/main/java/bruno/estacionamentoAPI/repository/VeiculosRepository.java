package bruno.estacionamentoAPI.repository;

import bruno.estacionamentoAPI.model.Estacionamento;
import bruno.estacionamentoAPI.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VeiculosRepository extends JpaRepository<Veiculo, Long> {

  Optional<Veiculo> findByPlaca(String placa);
  Optional<Veiculo> findByPlacaAndEstacionamento(String placa, Estacionamento estacionamento);
}
