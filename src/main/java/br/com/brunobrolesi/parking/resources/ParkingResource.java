package br.com.brunobrolesi.parking.resources;

import br.com.brunobrolesi.parking.domain.Address;
import br.com.brunobrolesi.parking.domain.Parking;
import br.com.brunobrolesi.parking.repositories.AddressRepository;
import br.com.brunobrolesi.parking.repositories.ParkingRepository;
import br.com.brunobrolesi.parking.resources.dto.ParkingDto;
import br.com.brunobrolesi.parking.resources.dto.ParkingResumedDto;
import br.com.brunobrolesi.parking.resources.form.UpdateParkingForm;
import br.com.brunobrolesi.parking.resources.form.ParkingForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/estabelecimento")
public class ParkingResource {

    @Autowired
    private ParkingRepository parkingRepository;
    @Autowired
    private AddressRepository addressRepository;

    @GetMapping
    public List<ParkingResumedDto> listParkings() {
        List<Parking> parkings = parkingRepository.findAll();
        return ParkingResumedDto.converter(parkings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDto> findParkingById(@PathVariable Integer id) {
        Optional<Parking> obj = parkingRepository.findById(id);
        if(obj.isPresent())
        {
            return ResponseEntity.ok().body(new ParkingDto(obj.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteParking(@PathVariable Integer id) {
        Optional<Parking> optional = parkingRepository.findById(id);
        if (optional.isPresent())
        {
            parkingRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ParkingDto> updateParking(@PathVariable Integer id, @RequestBody @Valid UpdateParkingForm form)
    {
        Optional<Parking> optional = parkingRepository.findById(id);
        if (optional.isPresent()){
            Parking parking = form.update(id, parkingRepository);
            return ResponseEntity.ok().body(new ParkingDto(parking));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ParkingDto> registerParking(@RequestBody @Valid ParkingForm form) {
        Parking parking = form.converterParking();
        Address address = parking.getAddresses().get(0);
        parkingRepository.save(parking);
        addressRepository.save(address);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}").buildAndExpand(parking.getId()).toUri();
        return ResponseEntity.created(uri).body(new ParkingDto(parking));
    }
}
