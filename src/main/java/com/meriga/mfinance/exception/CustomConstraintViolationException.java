package com.meriga.mfinance.exception;

public class CustomConstraintViolationException extends RuntimeException {
    public CustomConstraintViolationException(String ex) {
        super(ex);
    }
}
