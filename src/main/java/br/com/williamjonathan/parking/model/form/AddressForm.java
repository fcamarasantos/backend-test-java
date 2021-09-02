package br.com.williamjonathan.parking.model.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AddressForm {

    @NotBlank @NotNull
    @Pattern(regexp = "^\\d{5}-\\d{3}$")
    private String zipCode;

    @NotBlank @NotNull
    private String state;

    @NotBlank @NotNull
    private String city;

    @NotBlank @NotNull
    private String neighborhood;

    @NotBlank @NotNull
    private String street;

    @NotBlank @NotNull
    private String number;

    public AddressForm() {
    }

    public AddressForm(String zipCode, String state, String city, String neighborhood, String street, String number) {
        this.zipCode = zipCode;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.number = number;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
