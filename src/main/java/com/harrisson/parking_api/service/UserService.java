package com.harrisson.parking_api.service;

import com.harrisson.parking_api.model.User;
import com.harrisson.parking_api.repository.UserRepository;
import com.harrisson.parking_api.to.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
   private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;
    public User create(AuthRequest authRequest) {
        var password = passwordEncoder.encode(authRequest.password());
        var user = new User();
        user.setLogin(authRequest.login());
        user.setPassword(password);
        return repository.save(user);
    }
}
