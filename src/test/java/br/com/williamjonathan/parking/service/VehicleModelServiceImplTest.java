package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.VehicleModel;
import br.com.williamjonathan.parking.model.VehicleReport;
import br.com.williamjonathan.parking.repository.VehicleModelRepository;
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
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class VehicleModelServiceImplTest {

    @Mock
    private VehicleModelRepository vehicleModelRepository;

    @InjectMocks
    private VehicleModelServiceImpl vehicleModelService;

    @Test
    public void shouldFindOneVehicleModelByName() {
        VehicleModel vehicleModel = new VehicleModel();
        vehicleModel.setName("CIVIC");
        Optional<VehicleModel> optionalVehicleModel = Optional.of(vehicleModel);
        when(vehicleModelRepository.findByName( anyString() )).thenReturn(optionalVehicleModel);

        Optional<VehicleModel> optionalVehicleModelService = vehicleModelService.searchByName("CIVIC");
        assertEquals("CIVIC", optionalVehicleModelService.get().getName());
    }

    @Test
    public void shouldNotFindOneVehicleModelByName() {
        Optional<VehicleModel> optionalVehicleModel = vehicleModelService.searchByName("FIT");
        assertTrue(optionalVehicleModel.isEmpty());
    }

}