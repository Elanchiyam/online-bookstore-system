package org.bookstore.repository;

import org.bookstore.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {

    public Optional<Cart> findCartByUserId(String userId);
}
