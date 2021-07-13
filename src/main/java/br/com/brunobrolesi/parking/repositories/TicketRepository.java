package br.com.brunobrolesi.parking.repositories;

import br.com.brunobrolesi.parking.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository <Ticket, Integer> {

}
