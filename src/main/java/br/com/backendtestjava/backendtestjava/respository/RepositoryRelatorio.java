package br.com.backendtestjava.backendtestjava.respository;

import br.com.backendtestjava.backendtestjava.entity.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryRelatorio extends JpaRepository<Relatorio, Long> {
}
