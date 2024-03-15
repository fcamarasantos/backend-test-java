package org.restapiparkinglot.restapiparkinglot.repository;

import org.restapiparkinglot.restapiparkinglot.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}
