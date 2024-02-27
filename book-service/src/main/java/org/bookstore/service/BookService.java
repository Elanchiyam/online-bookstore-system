package org.bookstore.service;

import org.bookstore.exception.BookAlreadyExists;
import org.bookstore.exception.BookNotFoundException;
import org.bookstore.model.Book;
import org.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public String addNewBook(Book book){
        if(repository.findBookByIsbn(book.getIsbn()).isEmpty()) {
            repository.save(book);
            return "Book added Successfully";
        } else
            throw new BookNotFoundException("Book: Title:-" + book.getTitle() + " :: ISBN:-" + book.getIsbn() +" is already exists!");
    }

    public String deleteBook(String isbn){
        if(repository.findBookByIsbn(isbn).isPresent()){
            repository.deleteBookByIsbn(isbn);
            return "Book deleted Successfully";
        } else
            throw new BookAlreadyExists("Book with ISBN:" + isbn + " not Exists!");
    }

    public List<Book> getAllBooks(){
        return repository.findAll();
    }

    public Book getBookByIsbn(String isbn){
          return repository.findBookByIsbn(isbn)
                .orElseThrow(()->new BookNotFoundException("Book with ISBN: "+ isbn +"Not Exists"));
    }

    public List<Book> searchBookByAuthor(String author){
        List<Book> bookList = repository.findBookByAuthor(author);
        if(bookList.isEmpty())
            throw new BookNotFoundException("Book Not Exists with author name : "+author);
        else
            return bookList;

    }
}
