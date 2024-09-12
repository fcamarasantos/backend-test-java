package com.harrisson.parking_api.to;

import com.harrisson.parking_api.model.Establishment;

public record EstablishmentList(
        String name,
        String cnpj,
        String phone,
        Integer motorcycleQuantity,
        Integer carQuantity,
        Boolean active
) {
    public EstablishmentList(Establishment establishment) {
        this(establishment.getName(),
                establishment.getCnpj(),
                establishment.getPhone(),
                establishment.getMotorcycleQuantity(),
                establishment.getCarQuantity(),
                establishment.getActive());
    }

    public EstablishmentList() {
        this(null, null, null, null, null, null);
    }
}
