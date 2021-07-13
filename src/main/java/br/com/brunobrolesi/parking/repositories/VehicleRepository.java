package br.com.brunobrolesi.parking.repositories;

import br.com.brunobrolesi.parking.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository <Vehicle, Integer> {

    @Query("select v from Vehicle v where license_plate = ?1")
    Optional<Vehicle> findByLicensePlate(String vehiclePlate);
}
