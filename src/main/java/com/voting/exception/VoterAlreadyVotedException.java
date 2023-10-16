package com.voting.exception;

public class VoterAlreadyVotedException extends Exception {
    public VoterAlreadyVotedException(String message) {
        super(message);
    }
}
