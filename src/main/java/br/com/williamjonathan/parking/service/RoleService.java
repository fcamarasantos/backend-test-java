package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.Role;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RoleService {

    Optional<Role> searchByName(String name);

}
