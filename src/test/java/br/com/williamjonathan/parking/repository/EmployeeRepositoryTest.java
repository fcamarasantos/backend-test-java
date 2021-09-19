package br.com.williamjonathan.parking.repository;

import br.com.williamjonathan.parking.model.Address;
import br.com.williamjonathan.parking.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void shouldFindAEmployeeById() {
        Employee employee = new Employee();
        employee.setId(1L);

        employeeRepository.save(employee);
        Optional<Employee> optionalEmployee = employeeRepository.findById(employee.getId());

        assertTrue(optionalEmployee.isPresent());
        assertEquals(employee.getId(), optionalEmployee.get().getId());
    }

    @Test
    public void shouldNotFindAEmployeeBecauseDontExist() {
        Optional<Employee> optionalEmployee = employeeRepository.findById(2L);

        assertTrue(optionalEmployee.isEmpty());
    }

    @Test
    public void shouldFindAEmployeeByEmail() {
        Employee employee = new Employee();
        employee.setEmail("test@email.com");

        employeeRepository.save(employee);
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(employee.getEmail());

        assertTrue(optionalEmployee.isPresent());
    }
}