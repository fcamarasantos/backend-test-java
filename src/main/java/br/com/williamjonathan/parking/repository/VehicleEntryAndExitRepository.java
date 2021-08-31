package br.com.williamjonathan.parking.repository;

import br.com.williamjonathan.parking.model.VehicleEntryAndExit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleEntryAndExitRepository extends JpaRepository<VehicleEntryAndExit, Long> {
}
