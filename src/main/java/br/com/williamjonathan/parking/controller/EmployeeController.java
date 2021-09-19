package br.com.williamjonathan.parking.controller;

import br.com.williamjonathan.parking.model.form.EmployeeForm;
import br.com.williamjonathan.parking.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PutMapping
    public ResponseEntity<?> update(@RequestBody @Valid EmployeeForm form) {
        return employeeService.update(form);
    }

    @DeleteMapping
    public ResponseEntity<?> delete() {
        return employeeService.delete();
    }

}
