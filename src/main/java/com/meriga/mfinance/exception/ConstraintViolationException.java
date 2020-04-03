package com.meriga.mfinance.exception;

public class ConstraintViolationException extends RuntimeException {
    public ConstraintViolationException(String ex) {
        super(ex);
    }
}
