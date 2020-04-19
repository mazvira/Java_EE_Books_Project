package com.example.bookProject.controllers;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handle(final MethodArgumentNotValidException ex) {
        final List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errors);
    }
}