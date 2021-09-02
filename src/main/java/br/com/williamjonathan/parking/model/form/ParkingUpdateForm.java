package br.com.williamjonathan.parking.model.form;

import br.com.caelum.stella.bean.validation.CNPJ;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ParkingUpdateForm {

    @NotNull
    @NotBlank
    @CNPJ
    private String cnpj;

    @NotNull
    @NotBlank
    private String name;

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
