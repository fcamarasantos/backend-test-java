package br.com.williamjonathan.parking.model.form;


import br.com.caelum.stella.bean.validation.CNPJ;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ParkingForm {

    @NotNull @NotBlank
    @CNPJ
    private String cnpj;

    @NotNull @NotBlank
    private String name;

    @NotBlank @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    private String password;

    @NotBlank @NotNull
    @Pattern(regexp = "^\\d{5}-\\d{3}$")
    private String zipCode;

    @NotBlank @NotNull
    private String addressNumber;

    @NotBlank @NotNull
    @Pattern(regexp = "[0-9]{2}")
    private String ddd;

    @NotNull @NotBlank
    @Pattern(regexp= "^(\\d{5}|\\d{4})[- .]?\\d{4}$")
    private String phoneNumber;
    
    public ParkingForm(String cnpj, String name, String password, String zipCode, String ddd, String phoneNumber, String addressNumber) {
        this.cnpj = cnpj;
        this.name = name;
        this.password = password;
        this.zipCode = zipCode;
        this.ddd = ddd;
        this.phoneNumber = phoneNumber;
        this.addressNumber = addressNumber;
    }

    public PhoneForm phoneForm() {
        PhoneForm phoneForm = new PhoneForm();
        phoneForm.setDdd(ddd);
        phoneForm.setPhoneNumber(phoneNumber);

        return phoneForm;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

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

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    //vacancies

    //vehicles
}
