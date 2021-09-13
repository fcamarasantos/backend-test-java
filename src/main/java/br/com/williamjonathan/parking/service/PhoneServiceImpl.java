package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.Employee;
import br.com.williamjonathan.parking.model.Parking;
import br.com.williamjonathan.parking.model.Phone;
import br.com.williamjonathan.parking.model.dto.PhoneDto;
import br.com.williamjonathan.parking.model.form.ParkingForm;
import br.com.williamjonathan.parking.model.form.PhoneForm;
import br.com.williamjonathan.parking.repository.ParkingRepository;
import br.com.williamjonathan.parking.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PhoneServiceImpl implements PhoneService{

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private ParkingServiceImpl parkingService;

    @Override
    public List<Phone> create(ParkingForm form, Parking parking) {
        List<Phone> phones = new ArrayList<>();
        Phone phone = new Phone(form.getDdd(), form.getPhoneNumber(), parking);
        phones.add(phone);

        phoneRepository.save(phone);

        return phones;
    }

    @Override
    public ResponseEntity<?> create(PhoneForm form) {
        Employee employee = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Parking> optionalParking = parkingService.searchById(employee.getParking().getId());
        if(optionalParking.isPresent()) {
            Parking parking = optionalParking.get();
            Phone phone = new Phone(form.getDdd(), form.getPhoneNumber(), optionalParking.get());
            Boolean exists = thisPhoneExistInThisParking(phone, parking);
            if(!exists) {

                phoneRepository.save(phone);
                PhoneDto phoneDto = new PhoneDto(phone);

                return new ResponseEntity<PhoneDto>(phoneDto, HttpStatus.CREATED);
            }
            return ResponseEntity.badRequest().build(); // Exception de phone exist
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> read(Long id) {
        Optional<Phone> optionalPhone = phoneRepository.findById(id);
        if(optionalPhone.isPresent()) {
            if(thisPhoneBelongAThisParkingEmployee(optionalPhone.get())) {
                Phone phoneBuild = optionalPhone.get();
                PhoneDto phone = new PhoneDto(phoneBuild);

                return new ResponseEntity<PhoneDto>(phone, HttpStatus.OK);
            }
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
        }

    @Override
    public ResponseEntity<?> update(PhoneForm form, Long id) {
        Optional<Phone> optionalPhone = phoneRepository.findById(id);
        if(optionalPhone.isPresent()) {
            Parking parking = optionalPhone.get().getParking();
            Phone phoneOfForm = new Phone(form.getDdd(), form.getPhoneNumber(), parking);
            Boolean exists = thisPhoneExistInThisParking(phoneOfForm, parking);
            if(!exists && thisPhoneBelongAThisParkingEmployee(optionalPhone.get())) {
                Phone phone = optionalPhone.get();
                phone.setDdd(form.getDdd());
                phone.setPhoneNumber(form.getPhoneNumber());
                phoneRepository.save(phone);

                PhoneDto phoneDto = new PhoneDto(phone);
                return new ResponseEntity<PhoneDto>(phoneDto, HttpStatus.OK);
            }
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();

    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        Optional<Phone> optionalPhone = phoneRepository.findById(id);
        if(optionalPhone.isPresent()) {
            if(thisPhoneBelongAThisParkingEmployee(optionalPhone.get())) {
                phoneRepository.deleteById(id);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public Boolean thisPhoneExistInThisParking(Phone phone, Parking parking) {
        List<Phone> phones = parking.getPhones();
        List<Phone> phonesFiltered = phones.stream().filter(p -> p.getDdd().equals(phone.getDdd()) && p.getPhoneNumber().equals(phone.getPhoneNumber()))
                .collect(Collectors.toList());

        if(phonesFiltered.size() == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean thisPhoneBelongAThisParkingEmployee(Phone phone) {
        Employee employee = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(phone.getParking().getId().equals(employee.getParking().getId())) {
            return true;
        }
        return false;
     }
}
