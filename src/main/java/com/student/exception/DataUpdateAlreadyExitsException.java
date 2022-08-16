package com.student.exception;

public class DataUpdateAlreadyExitsException extends  SQLCustomException {

    public DataUpdateAlreadyExitsException(String message) {
        super(message);
    }
}
