package br.com.williamjonathan.parking.repository;

import br.com.williamjonathan.parking.model.Parking;
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
public class ParkingRepositoryTest {

    @Autowired
    private ParkingRepository parkingRepository;

    @Test
    public void shouldFindAParkingById() {
        Parking parking = new Parking();
        parking.setId(1L);

        parkingRepository.save(parking);
        Optional<Parking> optionalParking = parkingRepository.findById(parking.getId());

        assertTrue(optionalParking.isPresent());
        assertEquals(parking.getId(), optionalParking.get().getId());
    }

    @Test
    public void shouldNotFindAParkingBecauseDontExist() {
        Optional<Parking> optionalParking = parkingRepository.findById(2L);

        assertTrue(optionalParking.isEmpty());
    }

    @Test
    public void shouldFindAParkingByCNPJ() {
        Parking parking = new Parking();
        parking.setCnpj("36.477.650/0001-70");

        parkingRepository.save(parking);
        Optional<Parking> optionalParking = parkingRepository.findByCnpj(parking.getCnpj());

        assertTrue(optionalParking.isPresent());
    }
}