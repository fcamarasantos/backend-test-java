package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.Parking;
import br.com.williamjonathan.parking.model.Type;
import br.com.williamjonathan.parking.model.Vacancy;
import br.com.williamjonathan.parking.model.form.ParkingForm;
import br.com.williamjonathan.parking.model.form.ParkingUpdateForm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ParkingService {

    ResponseEntity<?> create(ParkingForm form);

    ResponseEntity<?> read();

    ResponseEntity<?> update(ParkingUpdateForm parkingUpdateForm);

    ResponseEntity<?> delete();

    Optional<Parking> searchById(Long id);

    Vacancy getVacancyByType(Parking parking, Type type);

    boolean checkIfTheSizeofPhonesIsGreaterThanOne(Long id);
}

