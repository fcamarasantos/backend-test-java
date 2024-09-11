package com.harrisson.parking_api.repository;

import com.harrisson.parking_api.model.Establishment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {
    Page<Establishment> findAllByActiveTrue(Pageable page);
}
