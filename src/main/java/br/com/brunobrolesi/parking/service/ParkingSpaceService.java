package br.com.brunobrolesi.parking.service;

import br.com.brunobrolesi.parking.model.Address;
import br.com.brunobrolesi.parking.model.Parking;
import br.com.brunobrolesi.parking.model.ParkingSpace;
import br.com.brunobrolesi.parking.repositories.ParkingRepository;
import br.com.brunobrolesi.parking.repositories.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParkingSpaceService {

    @Autowired
    ParkingSpaceRepository parkingSpaceRepository;

    @Autowired
    ParkingRepository parkingRepository;


    public ParkingSpace findByParkingIdAndParkingSpaceId(Integer parkingId, Integer parkingSpaceId) {
        ParkingSpace obj = parkingSpaceRepository.findByParkingIdAndParkingSpaceId(parkingId, parkingSpaceId);
        return obj;
    }

    public ParkingSpace update(Integer parkingId, Integer parkingSpaceId, ParkingSpace parkingSpace) {
        ParkingSpace entity = parkingSpaceRepository.findByParkingIdAndParkingSpaceId(parkingId, parkingSpaceId);

        if (entity == null) return null;

        entity.setState(parkingSpace.getState());
        entity.setType(parkingSpace.getVehicleType());

        return parkingSpaceRepository.save(entity);
    }

    public boolean delete(Integer parkingId, Integer parkingSpaceId) {
        ParkingSpace parkingSpace = findByParkingIdAndParkingSpaceId(parkingId, parkingSpaceId);

        if (parkingSpace == null) return false;

        parkingSpaceRepository.deleteById(parkingSpace.getId());
        return true;
    }

    public ParkingSpace insert(Integer parkingId, ParkingSpace parkingSpace) {
        Optional<Parking> parking = parkingRepository.findById(parkingId);

        if (parking.isEmpty()) return null;

        parkingSpace.setParking(parking.get());
        
        return parkingSpaceRepository.save(parkingSpace);
    }
}
