package org.bookstore.advice;

import lombok.extern.slf4j.Slf4j;
import org.bookstore.dto.CustomErrorResponse;
import org.bookstore.dto.GlobalErrorCode;
import org.bookstore.exception.BookAlreadyExists;
import org.bookstore.exception.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class BookServiceGlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> handleBookNotFoundException(BookNotFoundException ex){
        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .errorCode(GlobalErrorCode.ERROR_BOOK_NOT_FOUND)
                .errorMessage(ex.getMessage())
                .build();
        log.error("BookServiceGlobalExceptionHandler::handleBookNotFoundException exception caught {}",ex.getMessage());
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    @ExceptionHandler(BookAlreadyExists.class)
    public ResponseEntity<?> handleBookAlreadyExists(BookAlreadyExists ex){
        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .status(HttpStatus.CONFLICT)
                .errorCode(GlobalErrorCode.ERROR_BOOK_ALREADY_EXISTS)
                .errorMessage(ex.getMessage())
                .build();
        log.error("BookServiceGlobalExceptionHandler::handleBookAlreadyExists exception caught {}",ex.getMessage());
        return ResponseEntity.internalServerError().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex){
        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorCode(GlobalErrorCode.GENERIC_ERROR)
                .errorMessage(ex.getMessage())
                .build();
        log.error("BookServiceGlobalExceptionHandler::handleGenericException exception caught {}",ex.getMessage());
        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
