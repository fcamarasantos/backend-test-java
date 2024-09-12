package com.fcamara.parking.api.services;

import com.fcamara.parking.api.domain.dto.EstablishmentPostDTO;
import com.fcamara.parking.api.domain.entities.Establishment;
import com.fcamara.parking.api.exceptions.ResourceNotFoundException;
import com.fcamara.parking.api.mappers.EstablishmentMapper;
import com.fcamara.parking.api.repositories.EstablishmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EstablishmentService {
    private final EstablishmentRepository establishmentRepository;

    public Establishment create(EstablishmentPostDTO establishment) {
        return establishmentRepository.save(EstablishmentMapper.toEntity(establishment));
    }

    public Establishment getById(Long id) {
        return establishmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Estabelecimento não encontrado"));
    }

    public List<Establishment> getAll() {
        return establishmentRepository.findAll();
    }

    public Establishment update(Long id, EstablishmentPostDTO establishment) {
        establishmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Estabelecimento não encontrado"));
        return establishmentRepository.save(EstablishmentMapper.toEntity(establishment));
    }

    public void delete(Long id) {
        establishmentRepository.deleteById(id);
    }
}
