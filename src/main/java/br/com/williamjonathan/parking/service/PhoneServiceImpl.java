package br.com.williamjonathan.parking.service;

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
    private ParkingRepository parkingRepository;

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
        Optional<Parking> optionalParking = parkingRepository.findById(form.getParkingId());
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
            Phone phoneBuild = optionalPhone.get();
            PhoneDto phone = new PhoneDto(phoneBuild);

            return new ResponseEntity<PhoneDto>(phone, HttpStatus.OK);
        }
            return ResponseEntity.notFound().build();
        }

    @Override
    public ResponseEntity<?> update(PhoneForm form, Long id) {
        Optional<Phone> optionalPhone = phoneRepository.findById(id);
        if(optionalPhone.isPresent()) {
            Phone phone = optionalPhone.get();
            phone.setDdd(form.getDdd());
            phone.setPhoneNumber(form.getPhoneNumber());

            phoneRepository.save(phone);

            PhoneDto phoneDto = new PhoneDto(phone);
            return new ResponseEntity<PhoneDto>(phoneDto, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();

    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        Optional<Phone> optionalPhone = phoneRepository.findById(id);
        if(optionalPhone.isPresent()) {
            phoneRepository.deleteById(id);
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

}
