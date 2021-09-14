package br.com.williamjonathan.parking.model.form;

import br.com.caelum.stella.bean.validation.CNPJ;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ParkingUpdateForm {

    @NotNull
    @NotBlank
    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "The zipcode need to have this structure XXXXX-XXX")
    private String cnpj;

    @NotNull
    @NotBlank
    @Size(min = 4, max = 16, message = "The name need to have on minimum 4 letters and maximum 16 letters")
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
