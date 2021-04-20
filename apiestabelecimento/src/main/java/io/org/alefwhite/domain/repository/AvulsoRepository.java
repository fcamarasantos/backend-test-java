package io.org.alefwhite.domain.repository;

import io.org.alefwhite.domain.entity.EntradaSaidaAvulso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvulsoRepository extends JpaRepository<EntradaSaidaAvulso, Integer> {
}
