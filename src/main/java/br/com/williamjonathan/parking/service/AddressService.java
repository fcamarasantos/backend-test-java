package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.Address;
import br.com.williamjonathan.parking.model.form.AddressForm;
import br.com.williamjonathan.parking.model.form.ParkingForm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {

    Address create(ParkingForm form);
    ResponseEntity<?> read();
    ResponseEntity<?> update(AddressForm form);
    ResponseEntity<?> delete();
}
