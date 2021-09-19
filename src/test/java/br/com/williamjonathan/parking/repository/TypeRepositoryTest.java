package br.com.williamjonathan.parking.repository;

import br.com.williamjonathan.parking.model.Role;
import br.com.williamjonathan.parking.model.Type;
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
public class TypeRepositoryTest {

    @Autowired
    private TypeRepository typeRepository;

    @Test
    public void shouldFindATypeById() {
        Type type = new Type();
        type.setId(1L);

        typeRepository.save(type);
        Optional<Type> optionalType = typeRepository.findById(type.getId());

        assertTrue(optionalType.isPresent());
        assertEquals(type.getId(), optionalType.get().getId());
    }

    @Test
    public void shouldNotFindATypeBecauseDontExist() {
        Optional<Type> optionalType = typeRepository.findById(2L);

        assertTrue(optionalType.isEmpty());
    }

    @Test
    public void shouldFindATypeByName() {
        Type type = new Type();
        type.setId(2L);
        type.setName("CAR");

        typeRepository.save(type);
        Optional<Type> optionalType = typeRepository.findByName(type.getName());

        assertTrue(optionalType.isPresent());
    }

}