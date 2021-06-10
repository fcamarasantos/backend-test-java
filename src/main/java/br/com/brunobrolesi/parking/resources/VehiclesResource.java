package br.com.brunobrolesi.parking.resources;

import br.com.brunobrolesi.parking.domain.Vehicle;
import br.com.brunobrolesi.parking.repositories.VehicleRepository;
import br.com.brunobrolesi.parking.resources.dto.VehicleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/veiculos")
public class VehiclesResource {

    @Autowired
    private VehicleRepository vehicleRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<VehicleDto> listVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return VehicleDto.converter(vehicles);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<VehicleDto> findVehicleById(@PathVariable Integer id) {
        Optional<Vehicle> obj = vehicleRepository.findById(id);
        if(obj.isPresent())
        {
            return ResponseEntity.ok().body(new VehicleDto(obj.get()));
        }
            return ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Vehicle> insertVehicle(@RequestBody Vehicle obj) {
        obj = vehicleRepository.save(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteVehicle(@PathVariable Integer id) {
        vehicleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
