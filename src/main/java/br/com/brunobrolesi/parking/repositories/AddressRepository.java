package br.com.brunobrolesi.parking.repositories;

import br.com.brunobrolesi.parking.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository <Address, Integer> {

    @Query("select a from Address a where parking_id = ?1 and a.id = ?2")
    Address findByParkingIdAndAddressId(Integer parkingId, Integer addressId);
}
