package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.Employee;
import br.com.williamjonathan.parking.model.Parking;
import br.com.williamjonathan.parking.model.Role;
import br.com.williamjonathan.parking.model.form.RegisterForm;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    Employee employeeBuilder(RegisterForm form, Parking parking, Role role);

    Long getParkingIdByEmployeeLogged();

    void deleteById(Long id);
}
