package com.voting.exception;

public class VoterAlreadyExistsException extends Exception {
    public VoterAlreadyExistsException(String message) {
        super(message);
    }
}
