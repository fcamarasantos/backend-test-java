package br.com.brunobrolesi.parking.repositories;

import br.com.brunobrolesi.parking.model.Address;
import br.com.brunobrolesi.parking.model.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpaceRepository extends JpaRepository <ParkingSpace, Integer> {

    @Query("select a from ParkingSpace a where parking_id = ?1 and a.id = ?2")
    ParkingSpace findByParkingIdAndAddressId(Integer parkingId, Integer parkingSpaceId);
}
