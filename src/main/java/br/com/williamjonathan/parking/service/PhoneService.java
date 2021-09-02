package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.Parking;
import br.com.williamjonathan.parking.model.Phone;
import br.com.williamjonathan.parking.model.form.ParkingForm;
import br.com.williamjonathan.parking.model.form.PhoneForm;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PhoneService{

    List<Phone> create(ParkingForm form, Parking parking);
    ResponseEntity<?> create(PhoneForm form);
    ResponseEntity<?> read(Long id);
    ResponseEntity<?> update(PhoneForm form, Long id);
    ResponseEntity<?> deleteById(Long id);
    Boolean thisPhoneExistInThisParking(Phone phone, Parking parking);
}
