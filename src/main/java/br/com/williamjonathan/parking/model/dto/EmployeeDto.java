package br.com.williamjonathan.parking.model.dto;

import br.com.williamjonathan.parking.model.Employee;


public class EmployeeDto {

    private Long id;

    private String email;

    private Long parkingId;

    private String roleName;

    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.email = employee.getEmail();
        this.parkingId = employee.getParking().getId();
        this.roleName = employee.getRole().getName();
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

    public Long getParkingId() {
        return parkingId;
    }

    public void setParkingId(Long parkingId) {
        this.parkingId = parkingId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
