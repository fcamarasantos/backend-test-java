package com.fcamara.parking.api.repositories;

import com.fcamara.parking.api.domain.entities.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {

    List<Parking> findByVehicleId(Long vehicleId);

    List<Parking> findByEstablishmentId(Long establishmentId);
}
