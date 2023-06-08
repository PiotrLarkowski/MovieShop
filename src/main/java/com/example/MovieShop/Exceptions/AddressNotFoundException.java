package com.example.MovieShop.Exceptions;

public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException(long id){
        super("Could not find Address: " + id);
    }
}
