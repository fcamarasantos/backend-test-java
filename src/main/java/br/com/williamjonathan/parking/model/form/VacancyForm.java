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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
