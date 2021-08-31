package br.com.williamjonathan.parking.repository;

import br.com.williamjonathan.parking.model.Vehicle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    void shouldFindAVehicle() {
        }
}