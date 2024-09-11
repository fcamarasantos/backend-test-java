package com.harrisson.parking_api.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {

    private String street;
    private String neighborhood;
    private String cep;
    private String city;
    private String uf;
    private String number;
    private String complement;

    public void updateAddress(Address addressData) {
        if (addressData.street != null) this.street = addressData.street;
        if (addressData.neighborhood != null) this.neighborhood = addressData.neighborhood;
        if (addressData.cep != null) this.cep = addressData.cep;
        if (addressData.city != null) this.city = addressData.city;
        if (addressData.uf != null) this.uf = addressData.uf;
        if (addressData.number != null) this.number = addressData.number;
        if (addressData.complement != null) this.complement = addressData.complement;

    }
}
