package com.harrisson.parking_api.to;

import com.harrisson.parking_api.model.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressData(
        @NotBlank
        String street,
        @NotBlank
        String neighborhood,
        @NotBlank
        @Pattern(regexp = "\\d{8}", message = "{cep.not.correct.size}")
        String cep,
        @NotBlank
        String city,
        @NotBlank(message = "{uf.not.blank}")
        String uf,

        String number,
        String complement
) {

}
