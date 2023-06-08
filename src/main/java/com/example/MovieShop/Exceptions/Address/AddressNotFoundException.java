package com.example.MovieShop.Exceptions.Address;

public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException(long id){
        super("Could not find Address: " + id);
    }
}
