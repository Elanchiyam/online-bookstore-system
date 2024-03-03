package org.bookstore.controller;

import org.bookstore.model.Cart;
import org.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService service;

    @PostMapping("/addToCart")
    public ResponseEntity<String> addToCart(@RequestParam String userId, @RequestParam String bookId, @RequestParam int newQuantity){
        return new ResponseEntity<>(service.addCartService(userId, bookId, newQuantity), HttpStatus.CREATED);
    }
}
