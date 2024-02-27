package org.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document(collection = "order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private String orderId;
    private String userId;
    private LocalDate orderDate;
    private Double totalPrice;
    private String status;
    private  String deliveryAddress;
    private String paymentMethod;
    private List<Book> bookId;
}
