package br.com.brunobrolesi.parking.controller;

import br.com.brunobrolesi.parking.controller.form.*;
import br.com.brunobrolesi.parking.model.Address;
import br.com.brunobrolesi.parking.model.Parking;
import br.com.brunobrolesi.parking.controller.dto.ParkingDto;
import br.com.brunobrolesi.parking.controller.dto.ParkingResumedDto;
import br.com.brunobrolesi.parking.model.ParkingSpace;
import br.com.brunobrolesi.parking.model.Ticket;
import br.com.brunobrolesi.parking.service.AddressService;
import br.com.brunobrolesi.parking.service.ParkingService;
import br.com.brunobrolesi.parking.service.ParkingSpaceService;
import br.com.brunobrolesi.parking.service.TicketService;
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

    @Autowired
    private ParkingSpaceService parkingSpaceService;

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public List<ParkingResumedDto> listParkings() {
        List<Parking> parkings = parkingService.findAll();
        return ParkingResumedDto.converter(parkings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDto> findParkingById(@PathVariable Integer id) {
        Optional<Parking> optional = Optional.ofNullable(parkingService.findById(id));
        if(optional.isPresent())
        {
            return ResponseEntity.ok().body(new ParkingDto(optional.get()));
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

    @PutMapping("/{parkingId}/endereco/{addressId}")
    @Transactional
    public ResponseEntity<Address> updateAddress(@PathVariable Integer parkingId, @PathVariable Integer addressId, @RequestBody @Valid UpdateAddressForm form) {
        Optional<Address> optional = Optional.ofNullable(addressService.update(parkingId, addressId, form.converterAddress()));
        if (optional.isPresent()){
            return ResponseEntity.ok().body(optional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{parkingId}/endereco/{addressId}")
    @Transactional
    public ResponseEntity<Void> deleteAddress(@PathVariable Integer parkingId, @PathVariable Integer addressId) {
        boolean result = addressService.delete(parkingId, addressId);
        if (result)
        {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{parkingId}/endereco")
    @Transactional
    public ResponseEntity<Address> registerAddress(@PathVariable Integer parkingId, @RequestBody @Valid AddressForm form) {
        Address result = addressService.insert(parkingId, form.converterAddress());

        if (result == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(result);

    }

    @GetMapping("/{parkingId}/vaga/{parkingSpaceId}")
    public ResponseEntity<ParkingSpace> findParkingSpaceById(@PathVariable Integer parkingId, @PathVariable Integer parkingSpaceId)
    {
        ParkingSpace obj = parkingSpaceService.findByParkingIdAndParkingSpaceId(parkingId, parkingSpaceId);
        if(obj != null) return ResponseEntity.ok().body(obj);
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{parkingId}/vaga/{parkingSpaceId}")
    @Transactional
    public ResponseEntity<ParkingSpace> updateParkingSpace(@PathVariable Integer parkingId, @PathVariable Integer parkingSpaceId, @RequestBody @Valid UpdateParkingSpaceForm form) {
        ParkingSpace obj = parkingSpaceService.update(parkingId, parkingSpaceId, form.converterParkingSpace());
        if (obj != null){
            return ResponseEntity.ok().body(obj);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{parkingId}/vaga/{parkingSpaceId}")
    @Transactional
    public ResponseEntity<Void> deleteParkingSpace(@PathVariable Integer parkingId, @PathVariable Integer parkingSpaceId) {
        boolean result = parkingSpaceService.delete(parkingId, parkingSpaceId);
        if (result)
        {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{parkingId}/vaga")
    @Transactional
    public ResponseEntity<ParkingSpace> insertParkingSpace(@PathVariable Integer parkingId, @RequestBody @Valid ParkingSpaceForm form) {
        ParkingSpace obj = parkingSpaceService.insert(parkingId, form.converterParkingSpace());

        if (obj == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(obj);

    }

    @PostMapping("/{parkingId}/entrar")
    @Transactional
    public ResponseEntity<Ticket> vehicleEntryRequest(@PathVariable Integer parkingId, @RequestBody EntryTicketForm form) {
        Ticket obj = ticketService.entry(parkingId, form.getVehicleLicensePlate());

        if (obj == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(obj);

    }

    @PutMapping("/{parkingId}/sair")
    @Transactional
    public ResponseEntity<Ticket> vehicleExitRequest(@PathVariable Integer parkingId, @RequestBody ExitTicketForm form) {
        Ticket obj = ticketService.exit(parkingId, form.getId());

        if (obj == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(obj);

    }

}
