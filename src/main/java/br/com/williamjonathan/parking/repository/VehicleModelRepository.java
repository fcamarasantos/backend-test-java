package br.com.williamjonathan.parking.repository;

import br.com.williamjonathan.parking.model.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleModelRepository extends JpaRepository<VehicleModel, Long> {

    Optional<VehicleModel> findByName(String name);
}
