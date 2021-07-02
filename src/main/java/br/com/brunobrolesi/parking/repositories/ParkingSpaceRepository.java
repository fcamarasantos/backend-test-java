package br.com.brunobrolesi.parking.repositories;

import br.com.brunobrolesi.parking.model.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpaceRepository extends JpaRepository <ParkingSpace, Integer> {

}
