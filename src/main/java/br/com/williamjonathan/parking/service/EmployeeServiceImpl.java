package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.Employee;
import br.com.williamjonathan.parking.model.Parking;
import br.com.williamjonathan.parking.model.Role;
import br.com.williamjonathan.parking.model.form.EmployeeForm;
import br.com.williamjonathan.parking.model.form.RegisterForm;
import br.com.williamjonathan.parking.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee employeeBuilder(RegisterForm form, Parking parking, Role role) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordEncrypted = encoder.encode(form.getPassword());
        return new Employee(
                form.getEmail(),
                passwordEncrypted,
                parking,
                role);
    }

    @Override
    public Long getParkingIdByEmployeeLogged() {
        Employee employee = getLoggedEmployee();
        return employee.getParking().getId();
    }

    @Override
    public ResponseEntity<?> update(EmployeeForm form) {
        Employee employee = getLoggedEmployee();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(form.getPassword());
        employee.setPassword(password);
        employeeRepository.save(employee);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> delete() {
        Employee employee = getLoggedEmployee();
        employeeRepository.deleteById(employee.getId());
        return ResponseEntity.ok().build();
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee getLoggedEmployee() {
        return (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}
