package br.com.brunobrolesi.parking.repositories;

import br.com.brunobrolesi.parking.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository <City, Integer> {

}
