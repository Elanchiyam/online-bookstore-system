package org.bookstore.controller;

import org.bookstore.dto.AuthRequest;
import org.bookstore.entity.UserCredentials;
import org.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@RequestBody UserCredentials credentials){
        return new ResponseEntity<>(service.registerNewUser(credentials), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword()));
        if(authentication.isAuthenticated()) {
            return new ResponseEntity<>(authRequest.getUserName() + " login successfully", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Invalid User Request",HttpStatus.BAD_REQUEST);
    }
}
