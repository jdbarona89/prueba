package com.chubb.test.commun;

public class ClienteNoexiste extends RuntimeException {
    public ClienteNoexiste(String message) {
        super(message);
    }
}
