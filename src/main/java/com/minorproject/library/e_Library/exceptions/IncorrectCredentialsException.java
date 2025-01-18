package com.minorproject.library.e_Library.exceptions;

public class IncorrectCredentialsException extends RuntimeException{
    public IncorrectCredentialsException(String message){
        super(message);
    }
}
