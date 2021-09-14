package br.com.williamjonathan.parking.model.form;


import br.com.caelum.stella.bean.validation.CNPJ;

import javax.validation.constraints.*;

public class ParkingForm {

    @NotNull @NotBlank
    @CNPJ(message = "The cnpj need to have this structure XX.XXX.XXX/XXXX-XX")
    private String cnpj;

    @NotNull @NotBlank
    @Size(min = 4, max = 16, message = "The name need to have on minimum 4 letters and maximum 16 letters")
    private String name;

    @NotBlank @NotNull
    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "The zipcode need to have this structure XXXXX-XXX")
    private String zipCode;

    @NotBlank @NotNull
    private String addressNumber;

    @NotBlank @NotNull
    @Pattern(regexp = "[0-9]{2}", message = "The ddd need to have this structure XX")
    private String ddd;

    @NotNull @NotBlank
    @Pattern(regexp= "^(\\d{5}|\\d{4})[- .]?\\d{4}$", message = "The phone number need to have this structure XXXX-XXXX or XXXXX-XXXX")
    private String phoneNumber;
    
    public ParkingForm(String cnpj, String name, String zipCode, String ddd, String phoneNumber, String addressNumber) {
        this.cnpj = cnpj;
        this.name = name;
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


}
