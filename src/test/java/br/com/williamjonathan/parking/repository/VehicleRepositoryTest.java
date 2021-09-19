package br.com.williamjonathan.parking.repository;

import br.com.williamjonathan.parking.model.Address;
import br.com.williamjonathan.parking.model.Vehicle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    @org.junit.Test
    public void shouldFindAVehicleById() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);

        vehicleRepository.save(vehicle);
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicle.getId());

        assertTrue(optionalVehicle.isPresent());
        assertEquals(vehicle.getId(), optionalVehicle.get().getId());
    }

    @Test
    public void shouldNotFindAVehicleBecauseDontExist() {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(1L);

        assertTrue(optionalVehicle.isPresent());
    }
}