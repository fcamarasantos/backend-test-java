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

    public EstablishmentDataDetails() {
        this(null, null, null, null, null, null, null, null);

    }

    public Establishment toEntity() {
        Establishment establishment = new Establishment();
        establishment.setId(this.id);
        establishment.setName(this.name);
        establishment.setCnpj(this.cnpj);
        establishment.setAddress(this.address);
        establishment.setPhone(this.phone);
        establishment.setMotorcycleQuantity(this.motorcycleQuantity);
        establishment.setCarQuantity(this.carQuantity);
        return establishment;
    }
}
