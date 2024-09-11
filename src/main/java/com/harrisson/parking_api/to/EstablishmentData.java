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

    public Establishment toEntity() {
        Establishment establishment = new Establishment();
        establishment.setName(this.name);
        establishment.setCnpj(this.cnpj);
        establishment.setAddress(this.address);
        establishment.setPhone(this.phone);
        establishment.setMotorcycleQuantity(this.motorcycleQuantity);
        establishment.setCarQuantity(this.carQuantity);
        return establishment;
    }
}


