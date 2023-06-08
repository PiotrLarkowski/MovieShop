package com.example.MovieShop.Exceptions;

import com.example.MovieShop.Objects.Address;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.http.HttpResponse;

@ControllerAdvice
public class AddressNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(AddressNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpStatus addressNotFoundHandler(){
        return HttpStatus.NOT_FOUND;
    }
}
