package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.Employee;
import br.com.williamjonathan.parking.model.Parking;
import br.com.williamjonathan.parking.model.Role;
import br.com.williamjonathan.parking.model.form.EmployeeForm;
import br.com.williamjonathan.parking.model.form.RegisterForm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    Employee employeeBuilder(RegisterForm form, Parking parking, Role role);

    Long getParkingIdByEmployeeLogged();

    ResponseEntity<?> update(EmployeeForm form);

    ResponseEntity<?> delete();

    void deleteById(Long id);

    Employee getLoggedEmployee();
}
