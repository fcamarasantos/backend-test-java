package br.com.williamjonathan.parking.controller;

import br.com.williamjonathan.parking.model.form.AddressForm;
import br.com.williamjonathan.parking.service.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressServiceImpl addressService;

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> readJson() {
        return addressService.read();
    }

    @GetMapping(value = "xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> readXml() {
        return addressService.read();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid AddressForm addressForm) {
        return addressService.update(addressForm);
    }

}
