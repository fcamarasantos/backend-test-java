package br.com.brunobrolesi.parking.repositories;

import br.com.brunobrolesi.parking.domain.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends JpaRepository <Parking, Integer> {

}
