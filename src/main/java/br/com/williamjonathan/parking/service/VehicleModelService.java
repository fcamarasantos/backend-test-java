package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.VehicleModel;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface VehicleModelService {

    Optional<VehicleModel> searchByName(String name);
}
