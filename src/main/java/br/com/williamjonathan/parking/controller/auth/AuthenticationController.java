package br.com.williamjonathan.parking.controller.auth;

import br.com.williamjonathan.parking.model.form.LoginForm;
import br.com.williamjonathan.parking.service.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody LoginForm form) {
        return authenticationService.auth(form);
    }

}
