package br.com.brunobrolesi.parking.service;

import br.com.brunobrolesi.parking.model.Address;
import br.com.brunobrolesi.parking.model.ParkingSpace;
import br.com.brunobrolesi.parking.repositories.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingSpaceService {

    @Autowired
    ParkingSpaceRepository parkingSpaceRepository;


    public ParkingSpace findByParkingIdAndParkingSpaceId(Integer parkingId, Integer parkingSpaceId) {
        ParkingSpace obj = parkingSpaceRepository.findByParkingIdAndAddressId(parkingId, parkingSpaceId);
        return obj;
    }
}
