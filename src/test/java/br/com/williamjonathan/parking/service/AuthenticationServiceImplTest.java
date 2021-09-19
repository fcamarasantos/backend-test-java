package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.model.form.LoginForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class AuthenticationServiceImplTest {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Test
    public void shouldNotAuthenticateAndReturnABadRequestCode() {
        LoginForm loginForm = new LoginForm();
        loginForm.setEmail("test@email.com");
        loginForm.setPassword("1234");

        ResponseEntity<?> auth = authenticationService.auth(loginForm);
        assertEquals(auth.getStatusCode().toString(), "400 BAD_REQUEST");
    }
}