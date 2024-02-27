package org.bookstore.repository;

import org.bookstore.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserCredentials,Integer> {
    Optional<UserCredentials> findByUserName(String userName);
}
