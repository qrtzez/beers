package com.party.beers.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super("Not found!");
    }
}
