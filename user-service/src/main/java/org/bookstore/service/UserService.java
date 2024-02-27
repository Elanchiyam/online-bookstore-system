package org.bookstore.service;

import org.bookstore.entity.UserCredentials;
import org.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerNewUser(UserCredentials credentials){
        credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
        repository.save(credentials);
        return credentials.getUserName()+" is added to the database";
    }
}
