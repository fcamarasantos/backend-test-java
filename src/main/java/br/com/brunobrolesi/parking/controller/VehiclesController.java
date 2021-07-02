package br.com.brunobrolesi.parking.controller;

import br.com.brunobrolesi.parking.model.Vehicle;
import br.com.brunobrolesi.parking.repositories.VehicleRepository;
import br.com.brunobrolesi.parking.controller.dto.VehicleDto;
import br.com.brunobrolesi.parking.controller.form.UpdateVehicleForm;
import br.com.brunobrolesi.parking.controller.form.VehicleForm;
import br.com.brunobrolesi.parking.service.VehicleService;
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
@RequestMapping(value = "/veiculos")
public class VehiclesController {

    @Autowired
    private VehicleService service;

    @GetMapping
    public List<VehicleDto> listVehicles() {
        List<Vehicle> vehicles = service.findAll();
        return VehicleDto.converter(vehicles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDto> findVehicleById(@PathVariable Integer id) {
        Optional<Vehicle> obj = Optional.ofNullable(service.findById(id));
        if(obj.isPresent())
        {
            return ResponseEntity.ok().body(new VehicleDto(obj.get()));
        }
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<VehicleDto> registerVehicle(@RequestBody @Valid VehicleForm form) {
        Vehicle vehicle = form.converter();
        service.insert(vehicle);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}").buildAndExpand(vehicle.getId()).toUri();
        return ResponseEntity.created(uri).body(new VehicleDto(vehicle));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteVehicle(@PathVariable Integer id) {
        Optional<Vehicle> optional = Optional.ofNullable(service.findById(id));
        if (optional.isPresent())
        {
            service.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<VehicleDto> updateVehicle(@PathVariable Integer id, @RequestBody @Valid UpdateVehicleForm form)
    {
        Optional<Vehicle> optional = Optional.ofNullable(service.findById(id));
        if (optional.isPresent()){
            Vehicle vehicle = form.update(id);
            return ResponseEntity.ok().body(new VehicleDto(vehicle));
        }
        return ResponseEntity.notFound().build();
    }
}
