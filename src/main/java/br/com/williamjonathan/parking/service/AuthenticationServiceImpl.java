package br.com.williamjonathan.parking.service;

import br.com.williamjonathan.parking.config.security.TokenService;
import br.com.williamjonathan.parking.model.dto.TokenDto;
import br.com.williamjonathan.parking.model.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @Override
    public ResponseEntity<?> auth(LoginForm form) {
        UsernamePasswordAuthenticationToken login = form.convert();
        try {
            Authentication authentication = authManager.authenticate(login);

            String token = tokenService.generateToken(authentication);

            return new ResponseEntity<TokenDto>(new TokenDto(token, "Bearer"), HttpStatus.OK);
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
