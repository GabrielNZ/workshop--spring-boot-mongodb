package com.gabrielnz.workshopmongodb.service.exception;

public class DataBaseIntegrity extends RuntimeException {
    public DataBaseIntegrity(String message) {
        super(message);
    }
}
