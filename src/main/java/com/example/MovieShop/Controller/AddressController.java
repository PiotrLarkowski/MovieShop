package com.example.MovieShop.Controller;

import com.example.MovieShop.Objects.Address;
import com.example.MovieShop.ObjectsDto.AddressDto;
import com.example.MovieShop.Services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/address")
public class AddressController {
    private final AddressService addressService;
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    @PostMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Address createAddress(@RequestBody @Validated AddressDto addressDto, @PathVariable Long clientId) throws Exception{
        return addressService.createAddress(addressDto, clientId);
    }
    @PutMapping(path = "/{id}")
    public void updateAddress(@RequestBody @Validated AddressDto addressDto, @PathVariable Long id) {
        addressService.updateAddress(addressDto, id);
    }
    @GetMapping
    public List<Address> getAllAddresses(){
        return addressService.getAllAddress();
    }
    @GetMapping(path = "/{id}")
    public Address getAddressById(@PathVariable Long id){
        return addressService.getAddress(id);
    }
    @DeleteMapping(path = "/{id}")
    public void deleteAddressById(@PathVariable Long id){
        addressService.deleteAddress(id);
    }
}
