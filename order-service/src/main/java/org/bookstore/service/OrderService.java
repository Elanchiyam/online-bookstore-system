package org.bookstore.service;

import org.bookstore.model.Book;
import org.bookstore.model.Order;
import org.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order placeOrder(String userId, List<Book> books) {
        try {
            Order order = new Order();
            order.setUserId(userId);
            order.setOrderDate(LocalDate.now());

            double totalPrice = 0.0;
            for (Book book : books) {
                totalPrice += book.getPrice();
                int availableAvailability = book.getAvailability();
                if (availableAvailability > 0) {
                    book.setAvailability(availableAvailability - 1);
                    //                save book
                } else {
                    //                throw new BookOutOfStockException("Book out of stock: "+ book.getTitle());
                }
            }
            order.setTotalPrice(totalPrice);
            order.setStatus("Processing");
            return orderRepository.save(order);
        } catch (Exception e) {
            throw new RuntimeException("Failed to place order", e);
        }
    }
}
