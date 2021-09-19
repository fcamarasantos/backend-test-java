package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.Type;
import br.com.williamjonathan.parking.model.VehicleModel;
import br.com.williamjonathan.parking.repository.TypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class TypeServiceImplTest {

    @Mock
    private TypeRepository typeRepository;

    @InjectMocks
    private TypeServiceImpl typeService;

    @Test
    public void shouldFindOneTypeByName() {
        Type type = new Type();
        type.setName("CAR");
        Optional<Type> optionalType = Optional.of(type);
        when(typeRepository.findByName( anyString() )).thenReturn(optionalType);


        Optional<Type> optionalTypeService = typeService.searchByName("CAR");
        assertEquals("CAR", optionalTypeService.get().getName());
    }

    @Test
    public void shouldNotFindOneTypeByName() {
        Optional<Type> optionalType =  typeService.searchByName("MOTORCYCLE");
        assertTrue(optionalType.isEmpty());
    }

}