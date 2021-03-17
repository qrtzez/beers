package com.party.beers.exception;

public class IncorrectDataException extends RuntimeException{
    public IncorrectDataException() {
        super("Incorrect data!");
    }
}
