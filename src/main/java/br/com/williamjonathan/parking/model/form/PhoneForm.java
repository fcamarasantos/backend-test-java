package br.com.williamjonathan.parking.model.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PhoneForm {

    @NotBlank
    @NotNull
    @Pattern(regexp = "[0-9]{2}")
    private String ddd;

    @NotNull @NotBlank
    @Pattern(regexp= "(\\d{5}|\\d{4})[- .]?\\d{4}$")
    private String phoneNumber;

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
