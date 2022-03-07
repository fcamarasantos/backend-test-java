package br.com.backendtestjava.backendtestjava.respository;

import br.com.backendtestjava.backendtestjava.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryVeiculo extends JpaRepository<Veiculo, Long> {
}
