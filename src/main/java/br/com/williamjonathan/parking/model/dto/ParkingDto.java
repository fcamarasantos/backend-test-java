package br.com.williamjonathan.parking.model.dto;

import br.com.williamjonathan.parking.model.Address;
import br.com.williamjonathan.parking.model.Parking;
import br.com.williamjonathan.parking.model.Phone;
import br.com.williamjonathan.parking.model.Vacancy;
import org.modelmapper.ModelMapper;

import java.util.List;

public class ParkingDto {

    private Long id;
    private String cnpj;
    private String name;
    private Address address;
    private List<PhoneDto> phones;
    private List<Vacancy> vacancies;

    public ParkingDto() {
    }

    public ParkingDto(Long id, String cnpj, String name, Address address, List<PhoneDto> phones, List<Vacancy> vacancies) {
        this.id = id;
        this.cnpj = cnpj;
        this.name = name;
        this.address = address;
        this.phones = phones;
        this.vacancies = vacancies;
    }

    public ParkingDto(Parking parking) {
        this.id = parking.getId();
        this.cnpj = parking.getCnpj();
        this.name = parking.getName();
        this.address = parking.getAddress();
        this.phones = PhoneDto.convert(parking.getPhones());
        this.vacancies = parking.getVacancies();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<PhoneDto> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDto> phones) {
        this.phones = phones;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }
}
