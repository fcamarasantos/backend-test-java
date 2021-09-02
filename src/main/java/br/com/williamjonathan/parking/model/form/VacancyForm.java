package br.com.williamjonathan.parking.model.form;

import br.com.williamjonathan.parking.model.Parking;
import br.com.williamjonathan.parking.model.Type;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VacancyForm {

    @NotNull
    private Integer quantity;

    @NotNull
    private Integer quantityOcuppied;

    @NotNull
    private Long parking_id;

    @NotBlank
    @NotNull
    private String type;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantityOcuppied() {
        return quantityOcuppied;
    }

    public void setQuantityOcuppied(Integer quantityOcuppied) {
        this.quantityOcuppied = quantityOcuppied;
    }

    public Long getParking_id() {
        return parking_id;
    }

    public void setParking_id(Long parking_id) {
        this.parking_id = parking_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
