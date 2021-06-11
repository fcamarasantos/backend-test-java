package br.com.brunobrolesi.parking.resources.form;

import br.com.brunobrolesi.parking.domain.Address;
import br.com.brunobrolesi.parking.domain.Parking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ParkingForm {

    private String cnpj;
    private String name;
    private List<Address> addresses = new ArrayList<>();
    private Set<String> phones = new HashSet<>();

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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<String> getPhones() {
        return phones;
    }

    public void setPhones(Set<String> phones) {
        this.phones = phones;
    }

    public Parking converter() {
        return new Parking(null, cnpj, name);
    }
}
