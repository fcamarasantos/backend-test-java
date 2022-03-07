package br.com.backendtestjava.backendtestjava.respository;

import br.com.backendtestjava.backendtestjava.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryEmpresa extends JpaRepository<Empresa, Long> {
}
