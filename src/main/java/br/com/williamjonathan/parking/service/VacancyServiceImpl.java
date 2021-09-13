package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.Employee;
import br.com.williamjonathan.parking.model.Parking;
import br.com.williamjonathan.parking.model.Type;
import br.com.williamjonathan.parking.model.Vacancy;
import br.com.williamjonathan.parking.model.form.VacancyForm;
import br.com.williamjonathan.parking.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Employee employee = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Parking> optionalParking = parkingService.searchById(employee.getParking().getId());
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
    public ResponseEntity<?> delete(Long vacancyId) {
        Employee employee = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Parking> optionalParking = parkingService.searchById(employee.getParking().getId());
        Optional<Vacancy> optionalVacancy = vacancyRepository.findById(vacancyId);
        if(optionalParking.isPresent() && optionalVacancy.isPresent()) {
            if(!(optionalVacancy.get().getQuantityOcuppied() > 0)) {

                vacancyRepository.deleteById(vacancyId);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public Boolean parkingHasVacancyOfThisType(Parking parking, Type type) {
        for (Vacancy vacancy : parking.getVacancies()) {
            if(vacancy.getType().getId().equals(type.getId()) && vacancy.getQuantity() > vacancy.getQuantityOcuppied()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void increaseQuantityOcuppiedByType(Parking parking, Type type) {
        Vacancy vacancyByType = parkingService.getVacancyByType(parking, type);
        vacancyByType.setQuantityOcuppied(vacancyByType.getQuantityOcuppied() + 1);
        vacancyRepository.save(vacancyByType);
    }

    @Override
    public void decreaseQuantityOcuppiedByType(Parking parking, Type type) {
        Vacancy vacancyByType = parkingService.getVacancyByType(parking, type);
        vacancyByType.setQuantityOcuppied(vacancyByType.getQuantityOcuppied() - 1);
        vacancyRepository.save(vacancyByType);
    }


}
