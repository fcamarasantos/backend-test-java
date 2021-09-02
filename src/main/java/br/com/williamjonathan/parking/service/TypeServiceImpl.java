package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.Type;
import br.com.williamjonathan.parking.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeServiceImpl implements TypeService{

    @Autowired
    TypeRepository typeRepository;

    @Override
    public Optional<Type> searchByName(String typeName) {
        return typeRepository.findByName(typeName);
    }
}
