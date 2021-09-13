package br.com.williamjonathan.parking.model;

import br.com.williamjonathan.parking.model.form.PhoneForm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "parking")
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cnpj;

    private String name;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parking")
    private List<Phone> phones;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parking")
    private List<Vacancy> vacancies;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parking")
    private List<VehicleReport> vehicleReports;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parking")
    private List<Employee> employees;

    public Parking() {}

    public Parking(Long id, String cnpj, String name, Address address, List<Phone> phones, List<Vacancy> vacancies, List<VehicleReport> vehicleReports) {
        this.id = id;
        this.cnpj = cnpj;
        this.name = name;
        this.address = address;
        this.phones = phones;
        this.vacancies = vacancies;
        this.vehicleReports = vehicleReports;
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

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    public List<VehicleReport> getVehicleReports() {
        return vehicleReports;
    }

    public void setVehicleReports(List<VehicleReport> vehicleReports) {
        this.vehicleReports = vehicleReports;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
