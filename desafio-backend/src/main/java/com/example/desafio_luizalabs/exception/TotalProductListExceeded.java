package com.example.desafio_luizalabs.exception;

public class TotalProductListExceeded extends RuntimeException {
    public TotalProductListExceeded(String message) {
        super(message);
    }
}