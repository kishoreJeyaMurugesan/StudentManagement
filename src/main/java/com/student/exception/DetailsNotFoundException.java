package com.student.exception;

public class DetailsNotFoundException  extends SQLCustomException {

    public DetailsNotFoundException(String message) {
        super(message);
    }
}
