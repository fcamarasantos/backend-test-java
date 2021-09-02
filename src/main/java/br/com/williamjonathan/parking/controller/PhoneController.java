package br.com.williamjonathan.parking.controller;

import br.com.williamjonathan.parking.model.Parking;
import br.com.williamjonathan.parking.model.Phone;
import br.com.williamjonathan.parking.model.form.PhoneForm;
import br.com.williamjonathan.parking.service.PhoneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/phone")
public class PhoneController {

    @Autowired
    private PhoneServiceImpl phoneService;

    @GetMapping(value = "/{id}/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> readJson(@PathVariable Long id) {
        return phoneService.read(id);
    }

    @GetMapping(value = "/{id}/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> readXml(@PathVariable Long id) {
        return phoneService.read(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid PhoneForm phoneForm) {
        return phoneService.create(phoneForm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid PhoneForm phoneForm, @PathVariable Long id) {
        return phoneService.update(phoneForm, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return phoneService.deleteById(id);
    }
}
