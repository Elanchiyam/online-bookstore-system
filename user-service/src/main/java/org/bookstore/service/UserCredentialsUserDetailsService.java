package org.bookstore.service;

import org.bookstore.config.UserCredentialsUserDetails;
import org.bookstore.entity.UserCredentials;
import org.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserCredentialsUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserCredentialsUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredentials> credentials = repository.findByUserName(username);
        return credentials.map(UserCredentialsUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException(username + "user not found"));
    }
}
