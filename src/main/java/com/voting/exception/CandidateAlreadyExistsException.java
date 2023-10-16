package com.voting.exception;

public class CandidateAlreadyExistsException extends Exception {
    public CandidateAlreadyExistsException(String message) {
        super(message);
    }
}
