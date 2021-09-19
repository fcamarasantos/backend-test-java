package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.*;
import br.com.williamjonathan.parking.model.dto.ParkingAllDto;
import br.com.williamjonathan.parking.model.dto.ParkingDto;
import br.com.williamjonathan.parking.model.form.ParkingForm;
import br.com.williamjonathan.parking.model.form.ParkingUpdateForm;
import br.com.williamjonathan.parking.model.form.exception.CnpjDuplicateEntryException;
import br.com.williamjonathan.parking.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Override
    public ResponseEntity<ParkingDto> create(ParkingForm form) {
        Optional<Parking> optionalParking = parkingRepository.findByCnpj(form.getCnpj());
        if (optionalParking.isPresent()) {
            throw new CnpjDuplicateEntryException();
        }

        Parking parking = new Parking();
        parking.setCnpj(form.getCnpj());
        parking.setName(form.getName());

        Address address = addressService.create(form);
        parking.setAddress(address);

        parkingRepository.save(parking);

        List<Phone> phones = phoneService.create(form, parking);
        parking.setPhones(phones);

        ParkingDto parkingDto = new ParkingDto(parking);

        return new ResponseEntity<ParkingDto>(parkingDto,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> read() {
        Optional<Parking> optionalParking = parkingRepository.findById(employeeService.getParkingIdByEmployeeLogged());
        if(optionalParking.isPresent()) {
            ParkingDto parking = new ParkingDto(optionalParking.get());

            return ResponseEntity.ok(parking);
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> update(ParkingUpdateForm parkingUpdateForm) {
        Optional<Parking> optionalParking = parkingRepository.findById(employeeService.getParkingIdByEmployeeLogged());
        if(optionalParking.isPresent()) { // TRATAR UM ERRO DO NOME ATUALIZAR IGUAL
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
    public ResponseEntity<?> delete() {
        Optional<Parking> optionalParking = parkingRepository.findById(employeeService.getParkingIdByEmployeeLogged());
        if(optionalParking.isPresent()) {
            Parking parking = optionalParking.get();

            addressService.delete();

            List<Phone> phones = parking.getPhones();
            phones.forEach( p -> {
                phoneService.deleteById(p.getId());
            } );

            List<Employee> employees = parking.getEmployees();
            employees.forEach( e -> {
                employeeService.deleteById(e.getId());
            } );

            parkingRepository.deleteById(employeeService.getParkingIdByEmployeeLogged());

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public Optional<Parking> searchById(Long id) {
        return parkingRepository.findById(id);
    }

    @Override
    public Vacancy getVacancyByType(Parking parking, Type type) {
        Optional<Vacancy> optionalVacancy = parking.getVacancies()
                .stream()
                .filter(vacancy -> vacancy.getType().equals(type))
                .findFirst();
        return optionalVacancy.get();
    }

    @Override
    public boolean checkIfTheSizeofPhonesIsGreaterThanOne(Long id) {
        Optional<Parking> optionalParking = parkingRepository.findById(id);
        if(optionalParking.isPresent()) {
            List<Phone> phones = optionalParking.get().getPhones();
            return phones.size() > 1;
        }
        return false;
    }
}
