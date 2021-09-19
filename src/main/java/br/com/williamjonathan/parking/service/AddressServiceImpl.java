package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.config.handlers.JsonBodyHandler;
import br.com.williamjonathan.parking.model.Address;
import br.com.williamjonathan.parking.model.Employee;
import br.com.williamjonathan.parking.model.dto.ViaCepDto;
import br.com.williamjonathan.parking.model.form.AddressForm;
import br.com.williamjonathan.parking.model.form.ParkingForm;
import br.com.williamjonathan.parking.model.form.exception.ZipCodeException;
import br.com.williamjonathan.parking.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address create(ParkingForm form) {
        try {
            String zipCode = form.getZipCode();
            String zipCodeWithoutHyphen = zipCode.substring(0, 4) + zipCode.substring(6, 8);

            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(
                            URI.create("https://viacep.com.br/ws/" + "01324001" + "/json"))
                    .header("accept", "application/json")
                    .build();

            var response = client.send(request, new JsonBodyHandler<>(ViaCepDto.class)).body();

            Address address = new Address();
            address.setZipCode(zipCode);
            address.setState(response.getUf());
            address.setCity(response.getLocalidade());
            address.setNeighborhood(response.getBairro());
            address.setStreet(response.getLogradouro());
            address.setNumber(form.getAddressNumber());

            addressRepository.save(address);

            return address;
        } catch (Exception ex) {
            throw new ZipCodeException();
        }
    }

    @Override
    public ResponseEntity<?> update(AddressForm form) {
        Employee employee = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Address> optionalAddress = addressRepository.findById(employee.getParking().getAddress().getId());

        if(optionalAddress.isPresent()) {
            Address address = new Address(
                    optionalAddress.get().getId(),
                    form.getZipCode(),
                    form.getState(),
                    form.getCity(),
                    form.getNeighborhood(),
                    form.getStreet(),
                    form.getNumber());

            addressRepository.save(address);

            return new ResponseEntity<Address>(address,HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> read() {
        Employee employee = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Address> optionalAddress = addressRepository.findById(employee.getParking().getAddress().getId());

        if(optionalAddress.isPresent()) {
            return new ResponseEntity<Address>(optionalAddress.get(), HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> delete() {
        Employee employee = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long addressId = employee.getParking().getAddress().getId();
        Optional<Address> optionalAddress = addressRepository.findById(addressId);

        if(optionalAddress.isPresent()) {
            addressRepository.deleteById(addressId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
