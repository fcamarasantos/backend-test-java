package io.org.alefwhite.domain.repository;

import io.org.alefwhite.domain.entity.EntradaSaidaMensalista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensalistaRepository extends JpaRepository<EntradaSaidaMensalista, Integer> {
}
