package com.harrisson.parking_api.to;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record EstablishmentUpdateData(
        Long id,
        String name,
        String cnpj,
        AddressData address,
        String phone,
        Integer motorcycleQuantity,
        Integer carQuantity
) {    }


