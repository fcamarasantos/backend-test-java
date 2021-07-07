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
    AddressRepository addressRepository;

    public Address findByParkingIdAndAddressId(Integer parkingId, Integer addressId) {
        Address obj = addressRepository.findByParkingIdAndAddressId(parkingId, addressId);
        return obj;
    }

    public Address update(Integer parkingId, Integer addressId, Address updatedAddress) {
        Address entity = addressRepository.findByParkingIdAndAddressId(parkingId, addressId);

        if (entity == null) return null;

        entity.setStreet(updatedAddress.getStreet());
        entity.setNumber(updatedAddress.getNumber());
        entity.setAddress_2(updatedAddress.getAddress_2());

        return addressRepository.save(entity);
    }
}
