package com.example.producer.exception;

public class ChannelException extends RuntimeException {
    public ChannelException(String message, Throwable cause) {
        super(message, cause);
    }
}
