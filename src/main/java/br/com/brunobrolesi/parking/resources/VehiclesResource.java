package br.com.brunobrolesi.parking.resources;

import br.com.brunobrolesi.parking.domain.Vehicle;
import br.com.brunobrolesi.parking.repositories.VehicleRepository;
import br.com.brunobrolesi.parking.resources.dto.VehicleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
