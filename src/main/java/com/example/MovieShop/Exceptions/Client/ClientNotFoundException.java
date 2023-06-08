package com.example.MovieShop.Exceptions.Client;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(long id){
        super("Could not find client by id: " + id);
    }
}
