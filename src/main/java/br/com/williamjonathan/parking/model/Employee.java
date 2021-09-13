package br.com.williamjonathan.parking.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "employees")
public class Employee implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "parking_id", referencedColumnName = "id")
    private Parking parking;

    @ManyToOne
    private Role role;

    public Employee(String email, String password, Parking parking, Role role) {
        this.email = email;
        this.password = password;
        this.parking = parking;
        this.role = role;
    }

    public Employee(Long id, String email, String password, Parking parking, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.parking = parking;
        this.role = role;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        parking = parking;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<Role> roleList = new ArrayList<>();
        roleList.add(role);
        return roleList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
