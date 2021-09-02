package br.com.williamjonathan.parking.controller;

import br.com.williamjonathan.parking.model.dto.ParkingAllDto;
import br.com.williamjonathan.parking.model.form.ParkingForm;
import br.com.williamjonathan.parking.model.form.ParkingUpdateForm;
import br.com.williamjonathan.parking.service.ParkingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@RestController
@RequestMapping("/parking")
@XmlRootElement
public class ParkingController {

    @Autowired
    private ParkingServiceImpl parkingService;

    @GetMapping(value = "/json" ,produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> readAllJson() {
        return parkingService.readAll();
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    private ResponseEntity<?> readAllXml() {
        return parkingService.readAll();
    }

    @GetMapping(value = "/{id}/json", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> readByIdJson(@PathVariable(value = "id") Long id) {
        return parkingService.readById(id);
    }

    @GetMapping(value = "/{id}/xml", produces = MediaType.APPLICATION_XML_VALUE)
    private ResponseEntity<?> readByIdXml(@PathVariable(value = "id") Long id) {
        return parkingService.readById(id);
    }

    @PostMapping
    private ResponseEntity<?> create(@RequestBody @Valid ParkingForm form) {
        return parkingService.create(form);
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> update(@RequestBody ParkingUpdateForm form, @PathVariable Long id) {
        return parkingService.update(form, id);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id) {
        return parkingService.delete(id);
    }
}
