package com.chubb.test.commun;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ClienteNoexiste.class)
    public ResponseEntity<Void> handleClienteNoExiste(ClienteNoexiste ex) {
        return ResponseEntity.notFound().build();
    }

}