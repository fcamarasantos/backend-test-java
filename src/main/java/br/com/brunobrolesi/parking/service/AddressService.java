package br.com.brunobrolesi.parking.service;

import br.com.brunobrolesi.parking.model.Address;
import br.com.brunobrolesi.parking.repositories.AddressRepository;
import br.com.brunobrolesi.parking.repositories.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    ParkingRepository parkingRepository;

    @Autowired
    AddressRepository addressRepository;

    public Address findByParkingIdAndAddressId(Integer parkingId, Integer addressId) {
        Address obj = addressRepository.findByParkingIdAndAddressId(parkingId, addressId);
        return obj;
    }
}
