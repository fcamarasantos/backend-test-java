package com.harrisson.parking_api.model;

import com.harrisson.parking_api.to.EstablishmentData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "establishment", schema = "parking")
public class Establishment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String cnpj;
    @Embedded
    private Address address;
    private String phone;
    private Integer motorcycleQuantity; // Quantidade de vagas para motos.
    private Integer carQuantity; // Quantidade de vagas para carros.
    private Boolean active = true;

    public Establishment(EstablishmentData establishmentData) {
        this.name = establishmentData.name();
        this.cnpj = establishmentData.cnpj();
        this.address = establishmentData.address();
        this.phone = establishmentData.phone();
        this.motorcycleQuantity = establishmentData.motorcycleQuantity();
        this.carQuantity = establishmentData.carQuantity();
    }


    public void updateEstablishment(Establishment establishmenUpdateData) {
        if (establishmenUpdateData.name != null) this.name = establishmenUpdateData.name;
        if (establishmenUpdateData.cnpj != null) this.cnpj = establishmenUpdateData.cnpj;
        if (establishmenUpdateData.phone != null) this.phone = establishmenUpdateData.phone;
        if (establishmenUpdateData.address != null) this.address.updateAddress(establishmenUpdateData.address);
        if (establishmenUpdateData.motorcycleQuantity != null)
            this.motorcycleQuantity = establishmenUpdateData.motorcycleQuantity;
        if (establishmenUpdateData.carQuantity != null) this.carQuantity = establishmenUpdateData.carQuantity;

    }
}
