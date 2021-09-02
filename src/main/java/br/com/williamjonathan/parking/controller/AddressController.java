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

    @GetMapping(value = "/{id}/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> readJson(@PathVariable Long id) {
        return addressService.read(id);
    }

    @GetMapping(value = "/{id}/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> readXml(@PathVariable Long id) {
        return addressService.read(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid AddressForm addressForm, @PathVariable Long id) {
        return addressService.update(addressForm, id);
    }

}
