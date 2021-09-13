package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.form.LoginForm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    ResponseEntity<?> auth(LoginForm form);
}
