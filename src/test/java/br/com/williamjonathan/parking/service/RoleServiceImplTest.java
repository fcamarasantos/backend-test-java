package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.Role;
import br.com.williamjonathan.parking.model.Type;
import br.com.williamjonathan.parking.repository.RoleRepository;
import br.com.williamjonathan.parking.repository.TypeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Test
    public void shouldFindOneRoleByName() {
        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("ROLE_MANAGER");
        Optional<Role> role = Optional.of(role1);

        when(roleRepository.findByName( anyString() )).thenReturn(role);
        Optional<Role> optionalRole = roleService.searchByName("ROLE_MANAGER");
        assertTrue(optionalRole.isPresent());
    }

    @Test
    public void shouldNotFindOneRoleByName() {
        Optional<Role> optionalRole =  roleService.searchByName("ROLE_ANALYST");
        assertTrue(optionalRole.isEmpty());
    }

}