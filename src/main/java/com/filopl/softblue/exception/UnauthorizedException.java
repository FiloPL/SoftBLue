package com.filopl.softblue.exception;

/**
 * Created by T. Filo Zegarlicki on 10.06.2023
 **/

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}