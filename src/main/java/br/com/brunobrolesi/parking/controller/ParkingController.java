package br.com.brunobrolesi.parking.controller;

import br.com.brunobrolesi.parking.model.Address;
import br.com.brunobrolesi.parking.model.Parking;
import br.com.brunobrolesi.parking.controller.dto.ParkingDto;
import br.com.brunobrolesi.parking.controller.dto.ParkingResumedDto;
import br.com.brunobrolesi.parking.controller.form.UpdateParkingForm;
import br.com.brunobrolesi.parking.controller.form.ParkingForm;
import br.com.brunobrolesi.parking.service.AddressService;
import br.com.brunobrolesi.parking.service.ParkingService;
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
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private AddressService addressService;

    @GetMapping
    public List<ParkingResumedDto> listParkings() {
        List<Parking> parkings = parkingService.findAll();
        return ParkingResumedDto.converter(parkings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDto> findParkingById(@PathVariable Integer id) {
        Optional<Parking> obj = Optional.ofNullable(parkingService.findById(id));
        if(obj.isPresent())
        {
            return ResponseEntity.ok().body(new ParkingDto(obj.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteParking(@PathVariable Integer id) {
        Optional<Parking> optional = Optional.ofNullable(parkingService.findById(id));
        if (optional.isPresent())
        {
            parkingService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ParkingDto> registerParking(@RequestBody @Valid ParkingForm form) {
        Parking parking = form.converterParking();
        Parking inserted = parkingService.insert(parking);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}").buildAndExpand(inserted.getId()).toUri();
        return ResponseEntity.created(uri).body(new ParkingDto(inserted));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ParkingDto> updateParking(@PathVariable Integer id, @RequestBody @Valid UpdateParkingForm form)
    {
        Optional<Parking> optional = Optional.ofNullable(parkingService.update(id, form.converterParking()));
        if (optional.isPresent()){
            return ResponseEntity.ok().body(new ParkingDto(optional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{parkingId}/endereco/{addressId}")
    public ResponseEntity<Address> listAddress(@PathVariable Integer parkingId, @PathVariable Integer addressId)
    {
        Address obj = addressService.findByParkingIdAndAddressId(parkingId, addressId);
        if(obj != null) return ResponseEntity.ok().body(obj);
        return ResponseEntity.notFound().build();
    }

}
