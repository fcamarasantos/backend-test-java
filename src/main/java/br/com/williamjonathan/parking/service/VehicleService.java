package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.Parking;
import br.com.williamjonathan.parking.model.Vehicle;
import br.com.williamjonathan.parking.model.form.VehicleForm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface VehicleService {

    ResponseEntity<?> create(VehicleForm form);
    ResponseEntity<?> read(String licensePlate);

    ResponseEntity<?> park(Long parkingId, String licensePlate);
    ResponseEntity<?> unpark(String licensePlate);

    Boolean isParked(Vehicle vehicle);

    ResponseEntity<?> readVehicleLogged();
}
