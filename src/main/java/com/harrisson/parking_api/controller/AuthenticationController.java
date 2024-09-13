package com.harrisson.parking_api.controller;

import com.harrisson.parking_api.infra.security.TokenData;
import com.harrisson.parking_api.infra.security.TokenService;
import com.harrisson.parking_api.model.User;
import com.harrisson.parking_api.to.AuthRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "Authentication API")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Operation(summary = "Authenticate",
            description = "Default access to the API:\n\n"
                    + "login: admin@email.com\n\n"
                    + "password: admin")
    @PostMapping
    public ResponseEntity authenticate(@RequestBody @Valid AuthRequest authRequest) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(authRequest.login(), authRequest.password());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenData(tokenJWT));
    }
}
