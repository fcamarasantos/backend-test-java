package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.Parking;
import br.com.williamjonathan.parking.model.Type;
import br.com.williamjonathan.parking.model.Vacancy;
import br.com.williamjonathan.parking.model.form.VacancyForm;
import br.com.williamjonathan.parking.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VacancyServiceImpl implements VacancyService {

    @Autowired
    VacancyRepository vacancyRepository;

    @Autowired
    ParkingServiceImpl parkingService;

    @Autowired
    TypeServiceImpl typeService;

    @Override
    public ResponseEntity<?> create(VacancyForm form) {
        Optional<Parking> optionalParking = parkingService.searchById(form.getParking_id());
        Optional<Type> optionalType = typeService.searchByName(form.getType());

        if(optionalParking.isPresent() && optionalType.isPresent()) {

            Boolean parkingHasVacancyOfThisType = this.parkingHasVacancyOfThisType(optionalParking.get(), optionalType.get());

            if(!parkingHasVacancyOfThisType) {
                Vacancy vacancy = new Vacancy(
                        form.getQuantity(),
                        form.getQuantityOcuppied(),
                        optionalParking.get(),
                        optionalType.get()
                );
                vacancyRepository.save(vacancy);
                return new ResponseEntity<Vacancy>(vacancy ,HttpStatus.CREATED);
            };
            return ResponseEntity.badRequest().build(); // Exist vacancy of this type in this parking
        }
        return ResponseEntity.notFound().build(); // Parking or Type NOT FOUND
    }

    @Override
    public Boolean parkingHasVacancyOfThisType(Parking parking, Type type) {
        for (Vacancy vacancy : parking.getVacancies()) {
            if(vacancy.getType().getId().equals(type.getId())) {
                return true;
            }
        }
        return false;
    }


}
