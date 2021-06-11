package br.com.brunobrolesi.parking.repositories;

import br.com.brunobrolesi.parking.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository <Address, Integer> {

}
