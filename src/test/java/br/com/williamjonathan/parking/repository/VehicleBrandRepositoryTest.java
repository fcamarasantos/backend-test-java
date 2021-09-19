package br.com.williamjonathan.parking.repository;

import br.com.williamjonathan.parking.model.Role;
import br.com.williamjonathan.parking.model.VehicleBrand;
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
public class VehicleBrandRepositoryTest {

    @Autowired
    private VehicleBrandRepository vehicleBrandRepository;

    @Test
    public void shouldFindAVehicleBrandById() {
        VehicleBrand vehicleBrand = new VehicleBrand();
        vehicleBrand.setId(1L);

        vehicleBrandRepository.save(vehicleBrand);
        Optional<VehicleBrand> optionalVehicleBrand = vehicleBrandRepository.findById(vehicleBrand.getId());

        assertTrue(optionalVehicleBrand.isPresent());
        assertEquals(vehicleBrand.getId(), optionalVehicleBrand.get().getId());
    }

    @Test
    public void shouldNotFindAVehicleBrandBecauseDontExist() {
        Optional<VehicleBrand> optionalVehicleBrand = vehicleBrandRepository.findById(2L);

        assertTrue(optionalVehicleBrand.isEmpty());
    }


}