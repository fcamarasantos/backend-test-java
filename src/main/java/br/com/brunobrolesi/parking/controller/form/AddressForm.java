package br.com.brunobrolesi.parking.controller.form;

import br.com.brunobrolesi.parking.model.Address;
import br.com.brunobrolesi.parking.model.City;

public class AddressForm {

    private String street;
    private String number;
    private String address_2;
    private Integer cityId;

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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Address converterAddress() {
        City city = new City(cityId, null, null );
        Address address = new Address(null, street, number, address_2, null, city);
        return address;
    }
}
