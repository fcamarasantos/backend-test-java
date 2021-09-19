package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.dto.ParkingDto;
import br.com.williamjonathan.parking.model.form.ParkingForm;
import br.com.williamjonathan.parking.model.form.exception.ZipCodeException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ParkingServiceImplTest {



    @Autowired
    private ParkingServiceImpl parkingService;

    @Test
    public void shouldCreateAParking() {
        ParkingForm parkingForm = new ParkingForm("24.035.325/0001-78",
                "Parking",
                "00000-000",
                "11",
                "9999-9999",
                "200");

        ResponseEntity<ParkingDto> responseEntity = parkingService.create(parkingForm);
        assertEquals(responseEntity.getStatusCode().toString(), "201 CREATED");
    }

    @Test
    public void shouldNotCreateAParking() {
        ParkingForm parkingForm = new ParkingForm("77.285.616/0001-80",
                "Parking",
                "00000-",
                "11",
                "9999-9999",
                "200");

        assertThrows(ZipCodeException.class, () -> parkingService.create(parkingForm));
    }

    @Test
    public void shouldNotReturnOneParkingIfNotLogged() {
        ParkingForm parkingForm = new ParkingForm("83.441.452/0001-36",
                "Parking",
                "00000-000",
                "11",
                "9999-9999",
                "200");


        assertThrows(NullPointerException.class, () -> parkingService.read());
    }


}