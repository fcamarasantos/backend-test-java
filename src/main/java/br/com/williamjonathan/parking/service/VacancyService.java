package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.Parking;
import br.com.williamjonathan.parking.model.Type;
import br.com.williamjonathan.parking.model.form.VacancyForm;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface VacancyService {

    ResponseEntity<?> read();

    ResponseEntity<?> create(VacancyForm form);

    ResponseEntity<?> delete(Long vancancyId);

    Boolean parkingHasVacancyOfThisType(Parking parking, Type type);

    void increaseQuantityOcuppiedByType(Parking parking, Type type);

    void decreaseQuantityOcuppiedByType(Parking parking, Type type);

}
