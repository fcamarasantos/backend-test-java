package com.harrisson.parking_api.service;

import com.harrisson.parking_api.model.Establishment;
import com.harrisson.parking_api.repository.EstablishmentRepository;
import com.harrisson.parking_api.to.EstablishmentList;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstablishmentService {

    @Autowired
    private EstablishmentRepository repository;


    public Page<EstablishmentList> getEstablishments(Pageable page) {
        return repository.findAllByActiveTrue(page).map(EstablishmentList::new);
    }


    public void createEstablishment(Establishment establishment) {
        try {
            repository.save(establishment);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar Estabelecimento" + e.getCause());
        }
    }

    public void deleteEstablishment(Long id) {
        Optional<Establishment> establishmentOptional = repository.findById(id);
        if (establishmentOptional.isPresent()) {
            Establishment establishment = establishmentOptional.get();
            establishment.setActive(false);
            repository.save(establishment);
        } else {
            throw new RuntimeException("Estabelecimento não encontrado");
        }
    }

    public Establishment getById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
