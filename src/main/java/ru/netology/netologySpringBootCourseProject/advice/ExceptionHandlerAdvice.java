package ru.netology.netologySpringBootCourseProject.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.netologySpringBootCourseProject.exceptions.BadRequestExceptions;
import ru.netology.netologySpringBootCourseProject.exceptions.InternalServerErrorExceptions;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(BadRequestExceptions.class)
    public ResponseEntity<String> badRequestExceptionsHandler(BadRequestExceptions e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(InternalServerErrorExceptions.class)
    public ResponseEntity<String> internalServerErrorExceptionsHandler(InternalServerErrorExceptions e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }


}