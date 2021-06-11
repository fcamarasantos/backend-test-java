package br.com.brunobrolesi.parking.resources;

import br.com.brunobrolesi.parking.domain.Parking;
import br.com.brunobrolesi.parking.repositories.ParkingRepository;
import br.com.brunobrolesi.parking.resources.dto.ParkingDto;
import br.com.brunobrolesi.parking.resources.form.UpdateParkingForm;
import br.com.brunobrolesi.parking.resources.form.ParkingForm;
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
@RequestMapping(value = "/estabelecimento")
public class ParkingResource {

    @Autowired
    private ParkingRepository parkingRepository;

    @GetMapping
    public List<ParkingDto> listParkings() {
        List<Parking> parkings = parkingRepository.findAll();
        return ParkingDto.converter(parkings);
    }
}
