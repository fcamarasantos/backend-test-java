package br.com.brunobrolesi.parking.resources;

import br.com.brunobrolesi.parking.domain.Vehicle;
import br.com.brunobrolesi.parking.domain.VehicleType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/veiculos")
public class VehiclesResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Vehicle> listVehicles() {
        Vehicle vehicle1 = new Vehicle(
                "FORD",
                "Fusion",
                "2018",
                "Preto",
                "ALX8182",
                VehicleType.CARRO);

        Vehicle vehicle2 = new Vehicle(
                "Volkswagen",
                "Gol",
                "2018",
                "Branco",
                "ALB1234",
                VehicleType.CARRO);

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        return vehicles;
    }
}
