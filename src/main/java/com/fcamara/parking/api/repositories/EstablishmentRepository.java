package com.fcamara.parking.api.repositories;

import com.fcamara.parking.api.domain.entities.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {
}
