package com.example.mongo.exception;

public class EntityOperationException extends RuntimeException {
    public EntityOperationException(String message) {
        super(message);
    }
}
