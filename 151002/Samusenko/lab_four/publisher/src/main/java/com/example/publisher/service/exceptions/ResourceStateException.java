package com.example.publisher.service.exceptions;

public class ResourceStateException extends ResourceException{
    public ResourceStateException(int code, String message) {
        super(code, message);
    }
}
