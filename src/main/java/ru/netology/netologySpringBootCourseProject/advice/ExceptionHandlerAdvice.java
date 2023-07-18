package ru.netology.netologySpringBootCourseProject.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.netologySpringBootCourseProject.exceptions.InvalidTransactionExceptions;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(InvalidTransactionExceptions.class)
    public ResponseEntity<String> invalidTransaction(InvalidTransactionExceptions e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(e.getMessage());
    }
}