package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.Type;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TypeService {

    Optional<Type> searchByName(String typeName);
}
