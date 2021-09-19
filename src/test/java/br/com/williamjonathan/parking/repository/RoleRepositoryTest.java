package br.com.williamjonathan.parking.repository;

import br.com.williamjonathan.parking.model.Employee;
import br.com.williamjonathan.parking.model.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void shouldFindARoleById() {
        Role role = new Role();
        role.setId(1L);

        roleRepository.save(role);
        Optional<Role> optionalRole = roleRepository.findById(role.getId());

        assertTrue(optionalRole.isPresent());
        assertEquals(role.getId(), optionalRole.get().getId());
    }

    @Test
    public void shouldNotFindARoleBecauseDontExist() {
        Optional<Role> optionalRole = roleRepository.findById(2L);

        assertTrue(optionalRole.isEmpty());
    }

    @Test
    public void shouldFindARoleByName() {
        Role role = new Role();
        role.setName("MANAGER");

        roleRepository.save(role);
        Optional<Role> optionalRole = roleRepository.findByName(role.getName());

        assertTrue(optionalRole.isPresent());
    }

}