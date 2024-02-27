package org.bookstore.controller;

import org.bookstore.model.Book;
import org.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService service;

//    ADMIN

    @PostMapping("/addNewBook")
    public ResponseEntity<String> addNewBook(@RequestBody Book book){
        return new ResponseEntity<>(service.addNewBook(book),HttpStatus.CREATED);
    }

    @GetMapping("/deleteBook")
    public ResponseEntity<String> deleteBook(@RequestParam String isbn){
        return new ResponseEntity<>(service.deleteBook(isbn),HttpStatus.OK);
    }

//    USER
    @GetMapping("/getAllBooks")
    public List<Book> getAllBooks(){
        return service.getAllBooks();
    }

    @GetMapping("/getBookbyIsbn")
    public Book getBookbyIsbn(@RequestParam("isbn") String isbn){
        return service.getBookByIsbn(isbn);
    }

    @GetMapping("/searchBookByAuthor")
    public List<Book> searchBookByAuthor(@RequestParam("author") String author){
        return service.searchBookByAuthor(author);
    }
}
