package org.bookstore.repository;

import org.bookstore.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book,String> {

    public Optional<Book> findBookByIsbn(String isbn);
    public void deleteBookByIsbn(String isbn);
    public List<Book> findBookByAuthor(String author);
}
