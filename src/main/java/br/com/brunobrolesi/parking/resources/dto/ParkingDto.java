package br.com.brunobrolesi.parking.resources.dto;

import br.com.brunobrolesi.parking.domain.Address;
import br.com.brunobrolesi.parking.domain.Parking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ParkingDto {

    private Integer id;
    private String cnpj;
    private String name;
    private List<Address> addresses = new ArrayList<>();
    private Set<String> phones = new HashSet<>();

    public ParkingDto(Parking parking) {

        this.id = parking.getId();
        this.cnpj = parking.getCnpj();
        this.name = parking.getName();
        this.addresses = parking.getAddresses();
        this.phones = parking.getPhones();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public static List<ParkingDto> converter(List<Parking> parkings) {
        return parkings.stream().map(ParkingDto::new).collect(Collectors.toList());
    }
}
