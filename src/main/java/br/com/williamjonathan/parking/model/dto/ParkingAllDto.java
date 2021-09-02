package br.com.williamjonathan.parking.model.dto;

import br.com.williamjonathan.parking.model.Parking;

public class ParkingAllDto {

    private Long id;
    private String cnpj;
    private String name;

    public ParkingAllDto(Parking parking) {
        this.id = parking.getId();
        this.cnpj = parking.getCnpj();
        this.name = parking.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
