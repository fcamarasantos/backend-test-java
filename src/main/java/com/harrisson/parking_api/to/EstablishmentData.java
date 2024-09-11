package com.harrisson.parking_api.to;

import com.harrisson.parking_api.model.Address;
import com.harrisson.parking_api.model.Establishment;

public record EstablishmentData(
        String name,
        String cnpj,
        Address address,
        String phone,
        Integer motorcycleQuantity,
        Integer carQuantity
) {
    public EstablishmentData(Establishment establishment) {
        this(establishment.getName(), establishment.getCnpj(), establishment.getAddress(), establishment.getPhone(), establishment.getMotorcycleQuantity(), establishment.getCarQuantity());
    }
}


