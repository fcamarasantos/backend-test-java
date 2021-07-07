package br.com.brunobrolesi.parking.controller.form;

import br.com.brunobrolesi.parking.model.Address;

public class UpdateAddressForm {

    private String street;
    private String number;
    private String address_2;

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

    public String getAddress_2() {
        return address_2;
    }

    public void setAddress_2(String address_2) {
        this.address_2 = address_2;
    }

    public Address converterAddress() {
        Address address = new Address(null, street, number, address_2, null, null);
        return address;
    }
}
