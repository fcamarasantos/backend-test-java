package com.harrisson.parking_api.to;

import com.harrisson.parking_api.model.Address;
import com.harrisson.parking_api.model.Establishment;

public record EstablishmentDataDetails(
        Long id,
        String name,
        String cnpj,
        Address address,
        String phone,
        Integer motorcycleQuantity, // Quantidade de vagas para motos.
        Integer carQuantity, // Quantidade de vagas para carros.
        Boolean active
        ) {
    public EstablishmentDataDetails(Establishment establishment) {
        this(establishment.getId(),
                establishment.getName(),
                establishment.getCnpj(),
                establishment.getAddress(),
                establishment.getPhone(),
                establishment.getMotorcycleQuantity(),
                establishment.getCarQuantity(),
                establishment.getActive());
    }
}
