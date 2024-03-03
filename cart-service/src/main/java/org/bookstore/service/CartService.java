package org.bookstore.service;

import org.bookstore.model.Cart;
import org.bookstore.model.CartBooks;
import org.bookstore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository repository;

    public String addCartService(String userId, String bookId, int newQuantity){
        Optional<Cart> optionalCart = repository.findCartByUserId(userId);
        if(optionalCart.isPresent()){
//            Old User adding to his cart
            Cart userCart = optionalCart.get();
            List<CartBooks> cartBooks = userCart.getCartBooks();
            CartBooks book = cartBooks.stream().filter(bk -> bk.getBookId().equals(bookId)).findAny().orElse(null);
            if (book == null) {
                book = new CartBooks(bookId,newQuantity);
                cartBooks.add(book);
            } else {
                book.setQuantity(book.getQuantity() + newQuantity);
            }
            repository.save(userCart);
        } else {
//            New User
            Cart cart = new Cart();
            cart.setUserId(userId);
            List<CartBooks> cartBooks = new ArrayList<>();
            CartBooks cartBook = new CartBooks();
            cartBook.setBookId(bookId);
            cartBook.setQuantity(newQuantity);
            cartBooks.add(cartBook);
            cart.setCartBooks(cartBooks);
            repository.save(cart);
        }
        return "Cart added Successfully";
    }
}
