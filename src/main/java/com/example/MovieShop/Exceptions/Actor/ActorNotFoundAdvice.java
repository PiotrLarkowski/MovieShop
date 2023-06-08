package com.example.MovieShop.Exceptions.Actor;

import com.example.MovieShop.Exceptions.Address.AddressNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
public class ActorNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ActorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpStatus actorNotFoundHandler(){
        return HttpStatus.NOT_FOUND;
    }
}
