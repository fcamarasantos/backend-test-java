package com.harrisson.parking_api.service;

import com.harrisson.parking_api.model.Establishment;
import com.harrisson.parking_api.repository.EstablishmentRepository;
import com.harrisson.parking_api.to.EstablishmentData;
import com.harrisson.parking_api.to.EstablishmentDataDetails;
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

    public Establishment save(EstablishmentData establishmentData) {
        try {
            Establishment establishment = repository.save(establishmentData.toEntity());
            return establishment;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar Estabelecimento" + e.getCause());
        }
    }

    public void delete(Long id) {
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
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Establishment not found with id: " + id));
    }

    public Establishment update(EstablishmentDataDetails establishmentData) {
        Establishment establishment = getById(establishmentData.id());
        establishment.updateEstablishment(establishmentData.toEntity());
        return repository.save(establishment);
    }
}