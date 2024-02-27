package org.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private String BookId;
    private String title;
    private String author;
    private String isbn;
    private double price;
    private Integer availability;
}
