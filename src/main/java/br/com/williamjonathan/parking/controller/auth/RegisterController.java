package br.com.williamjonathan.parking.controller.auth;

import br.com.williamjonathan.parking.model.Employee;
import br.com.williamjonathan.parking.model.Parking;
import br.com.williamjonathan.parking.model.Role;
import br.com.williamjonathan.parking.model.dto.EmployeeDto;
import br.com.williamjonathan.parking.model.form.RegisterForm;
import br.com.williamjonathan.parking.repository.EmployeeRepository;
import br.com.williamjonathan.parking.service.EmployeeServiceImpl;
import br.com.williamjonathan.parking.service.ParkingServiceImpl;
import br.com.williamjonathan.parking.service.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private ParkingServiceImpl parkingService;

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody @Valid RegisterForm form) {
        Optional<Parking> optionalParking = parkingService.searchById(form.getParkingId());
        Optional<Role> optionalRole = roleService.searchByName("ROLE_" + form.getRoleName());
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(form.getEmail());

        if(optionalParking.isPresent() && optionalRole.isPresent()) {

            if(optionalEmployee.isEmpty()) {
                Employee employeeBuild = employeeService.employeeBuilder(
                        form,
                        optionalParking.get(),
                        optionalRole.get());
                employeeRepository.save(employeeBuild);

                EmployeeDto employee = new EmployeeDto(employeeBuild);

                return new ResponseEntity<EmployeeDto>(employee, HttpStatus.CREATED);
            }
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }
}
