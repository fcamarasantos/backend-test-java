package br.com.brunobrolesi.parking.repositories;

import br.com.brunobrolesi.parking.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository <State, Integer> {

}
