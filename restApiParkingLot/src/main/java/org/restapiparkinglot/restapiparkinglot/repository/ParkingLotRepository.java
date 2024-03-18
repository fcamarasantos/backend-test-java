package org.restapiparkinglot.restapiparkinglot.repository;

import org.restapiparkinglot.restapiparkinglot.model.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Integer> {
    Optional<ParkingLot> findByCnpj(String cnpj);

}
