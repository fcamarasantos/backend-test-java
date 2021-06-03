package fcamara.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fcamara.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, String>{

	Veiculo findByPlaca(String placa);

	void deleteByPlaca(String placa);

}
