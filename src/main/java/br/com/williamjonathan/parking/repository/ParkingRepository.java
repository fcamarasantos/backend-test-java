package br.com.williamjonathan.parking.repository;

import br.com.williamjonathan.parking.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {

    Optional<Parking> findByCnpj(String cnpj);
    Optional<Parking> findByName(String name);
}
