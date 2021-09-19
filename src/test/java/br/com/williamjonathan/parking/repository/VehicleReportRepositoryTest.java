package br.com.williamjonathan.parking.repository;

import br.com.williamjonathan.parking.model.Parking;
import br.com.williamjonathan.parking.model.VehicleModel;
import br.com.williamjonathan.parking.model.VehicleReport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class VehicleReportRepositoryTest {

    @Autowired
    private VehicleReportRepository vehicleReportRepository;

    @Test
    public void shouldFindAVehicleReportById() {
        VehicleReport vehicleReport = new VehicleReport();
        vehicleReport.setId(1L);

        vehicleReportRepository.save(vehicleReport);
        Optional<VehicleReport> optionalVehicleReport = vehicleReportRepository.findById(vehicleReport.getId());

        assertTrue(optionalVehicleReport.isPresent());
        assertEquals(vehicleReport.getId(), optionalVehicleReport.get().getId());
    }

    @Test
    public void shouldNotFindAVehicleReportBecauseDontExist() {
        Optional<VehicleReport> optionalVehicleReport = vehicleReportRepository.findById(2L);

        assertTrue(optionalVehicleReport.isEmpty());
    }


}