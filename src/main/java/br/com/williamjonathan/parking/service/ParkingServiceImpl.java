package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.Address;
import br.com.williamjonathan.parking.model.Parking;
import br.com.williamjonathan.parking.model.Phone;
import br.com.williamjonathan.parking.model.dto.ParkingAllDto;
import br.com.williamjonathan.parking.model.dto.ParkingDto;
import br.com.williamjonathan.parking.model.form.ParkingForm;
import br.com.williamjonathan.parking.model.form.ParkingUpdateForm;
import br.com.williamjonathan.parking.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private PhoneServiceImpl phoneService;

    @Autowired
    private AddressServiceImpl addressService;

    @Override
    public ResponseEntity<ParkingDto> create(ParkingForm form) {
        Parking parking = new Parking();
        parking.setCnpj(form.getCnpj());
        parking.setName(form.getName());
        parking.setPassword(form.getPassword());

        Address address = addressService.create(form);
        parking.setAddress(address);

        parkingRepository.save(parking);

        List<Phone> phones = phoneService.create(form, parking);
        parking.setPhones(phones);

        ParkingDto parkingDto = new ParkingDto(parking);

        return new ResponseEntity<ParkingDto>(parkingDto,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> readById(Long id) {
        Optional<Parking> optionalParking = parkingRepository.findById(id);
        if(optionalParking.isPresent()) {
            ParkingDto parking = new ParkingDto(optionalParking.get());

            return ResponseEntity.ok(parking);
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> readAll() {
        List<Parking> parkingBuild = parkingRepository.findAll();
        List<ParkingAllDto> parking = new ArrayList<>();
        parkingBuild.forEach(pBuild -> {
            ParkingAllDto p = new ParkingAllDto(pBuild);
            parking.add(p);
        });

        return new ResponseEntity<Object>(parking, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> update(ParkingUpdateForm parkingUpdateForm, Long id) {
        Optional<Parking> optionalParking = parkingRepository.findById(id);
        if(optionalParking.isPresent()) {
            Optional<Parking> optionalParkingByCnpj = parkingRepository.findByCnpj(parkingUpdateForm.getCnpj());
            Optional<Parking> optionalParkinByName =parkingRepository.findByName(parkingUpdateForm.getName());

            if(optionalParkingByCnpj.isEmpty() || optionalParkinByName.isEmpty()) {
                optionalParking.get()
                        .setCnpj(parkingUpdateForm.getCnpj());
                optionalParking.get()
                        .setName(parkingUpdateForm.getName());

                parkingRepository.save(optionalParking.get());

                ParkingAllDto parkingDto = new ParkingAllDto(optionalParking.get());

                return new ResponseEntity<ParkingAllDto>(parkingDto, HttpStatus.OK);
            }
            return ResponseEntity.badRequest().build(); // Exception of name or cnpj exists
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Optional<Parking> optionalParking = parkingRepository.findById(id);
        if(optionalParking.isPresent()) {
            Parking parking = optionalParking.get();

            addressService.deleteById(parking.getAddress().getId());

            List<Phone> phones = parking.getPhones();
            phones.forEach( phone -> {
                phoneService.deleteById(phone.getId());
            } );

            parkingRepository.deleteById(id);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public Optional<Parking> searchById(Long id) {
        return parkingRepository.findById(id);
    }
}
