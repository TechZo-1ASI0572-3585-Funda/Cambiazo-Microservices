package com.cambiazo.donation.domain.exceptions;

public class OngNotFoundException extends RuntimeException{

    public OngNotFoundException(Long aLong) {
        super("Ong with id " + aLong + " not found");
    }
}
