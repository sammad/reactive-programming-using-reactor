package com.learnreactiveprogramming.exception;

public class UnregisteredSubscriberException extends RuntimeException {
    public UnregisteredSubscriberException(String message) {
        super(message);
    }
}
