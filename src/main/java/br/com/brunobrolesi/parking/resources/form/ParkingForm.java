package br.com.brunobrolesi.parking.resources.form;

import br.com.brunobrolesi.parking.domain.Address;
import br.com.brunobrolesi.parking.domain.City;
import br.com.brunobrolesi.parking.domain.Parking;

import java.util.HashSet;
import java.util.Set;


public class ParkingForm {

    private String cnpj;
    private String name;

    private String street;
    private String number;
    private String address_2;
    private Integer cityId;

    private String phone1;
    private String phone2;

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

    public void setCityId(Integer cityID) {
        this.cityId = cityID;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public Parking converterParking() {
        Parking parking = new Parking(null, cnpj, name);
        City city = new City(cityId, null, null);
        Address address = new Address(null, street, number, address_2, parking, city);
        parking.getAddresses().add(address);
        parking.getPhones().add(phone1);
        if(phone2 != null){
            parking.getPhones().add(phone2);
        }
        return parking;
    }

}
