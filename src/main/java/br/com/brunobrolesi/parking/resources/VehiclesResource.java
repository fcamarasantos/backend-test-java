package br.com.brunobrolesi.parking.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/veiculos")
public class VehiclesResource {

    @RequestMapping(method = RequestMethod.GET)
    public String listar() {
        return "Rest está funcionando";
    }
}
