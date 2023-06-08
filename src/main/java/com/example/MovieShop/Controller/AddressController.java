package com.example.MovieShop.Controller;

import com.example.MovieShop.Objects.Address;
import com.example.MovieShop.ObjectsDto.AddressDto;
import com.example.MovieShop.Services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Address createAddress(@RequestBody @Validated AddressDto addressDto) throws Exception{
        return addressService.createAddress(addressDto);
    }
}
