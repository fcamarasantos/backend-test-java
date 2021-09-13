package br.com.williamjonathan.parking.controller;

import br.com.williamjonathan.parking.model.form.ParkingForm;
import br.com.williamjonathan.parking.model.form.ParkingUpdateForm;
import br.com.williamjonathan.parking.service.ParkingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlRootElement;

@RestController
@RequestMapping("/parking")
@XmlRootElement
public class ParkingController {

    @Autowired
    private ParkingServiceImpl parkingService;

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> readJson() {
        return parkingService.read();
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    private ResponseEntity<?> readXml() {
        return parkingService.read();
    }

    @PostMapping
    private ResponseEntity<?> create(@RequestBody @Valid ParkingForm form) {
        return parkingService.create(form);
    }

    @PutMapping
    private ResponseEntity<?> update(@RequestBody ParkingUpdateForm form) {
        return parkingService.update(form);
    }

    @DeleteMapping
    private ResponseEntity<?> delete() {
        return parkingService.delete();
    }
}
