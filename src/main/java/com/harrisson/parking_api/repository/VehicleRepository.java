package com.harrisson.parking_api.repository;

import com.harrisson.parking_api.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Vehicle findByPlate(String licensePlate);
}
