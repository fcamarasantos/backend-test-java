package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.Parking;
import br.com.williamjonathan.parking.model.Type;
import br.com.williamjonathan.parking.model.form.VacancyForm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface VacancyService {

    ResponseEntity<?> create(VacancyForm form);

    Boolean parkingHasVacancyOfThisType(Parking parking, Type type);
}
