package br.com.williamjonathan.parking.config.security;

import br.com.williamjonathan.parking.model.Employee;
import br.com.williamjonathan.parking.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationConfigService implements UserDetailsService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Employee> optionalEmployee = repository.findByEmail(email);

        if(optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        }
        throw new RuntimeException(); // Create exception
    }
}
