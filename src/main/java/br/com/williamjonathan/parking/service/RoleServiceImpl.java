package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.Role;
import br.com.williamjonathan.parking.repository.RoleRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository repository;

    @Override
    public Optional<Role> searchByName(String name) {
        return repository.findByName(name);
    }

}
