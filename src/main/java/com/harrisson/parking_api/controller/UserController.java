package com.harrisson.parking_api.controller;

import com.harrisson.parking_api.service.UserService;
import com.harrisson.parking_api.to.AuthRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "User Create to access the API")
@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearer-key")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity createUser(AuthRequest authRequest) {
        var user = userService.create(authRequest);
        return ResponseEntity.ok(user);
    }
}
