package br.com.williamjonathan.parking.repository;

import br.com.williamjonathan.parking.model.Employee;
import br.com.williamjonathan.parking.model.VehicleModel;
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
public class VehicleModelRepositoryTest {

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    @Test
    public void shouldFindAVehicleModelById() {
        VehicleModel vehicleModel = new VehicleModel();
        vehicleModel.setId(1L);

        vehicleModelRepository.save(vehicleModel);
        Optional<VehicleModel> optionalVehicleModel = vehicleModelRepository.findById(vehicleModel.getId());

        assertTrue(optionalVehicleModel.isPresent());
        assertEquals(vehicleModel.getId(), optionalVehicleModel.get().getId());
    }

    @Test
    public void shouldNotFindAVehicleModelBecauseDontExist() {
        Optional<VehicleModel> optionalVehicleModel = vehicleModelRepository.findById(2L);

        assertTrue(optionalVehicleModel.isEmpty());
    }

    @Test
    public void shouldFindAVehicleModelByName() {
        VehicleModel vehicleModel = new VehicleModel();
        vehicleModel.setName("CIVIC");

        vehicleModelRepository.save(vehicleModel);
        Optional<VehicleModel> optionalVehicleModel = vehicleModelRepository.findByName("CIVIC");

        assertTrue(optionalVehicleModel.isPresent());
    }

}