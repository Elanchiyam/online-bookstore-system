package org.bookstore.exception;

public class BookAlreadyExists extends RuntimeException{
    public BookAlreadyExists(String message){
        super(message);
    }
}
