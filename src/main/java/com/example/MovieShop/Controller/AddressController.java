package com.example.MovieShop.Controller;

import com.example.MovieShop.Objects.Address;
import com.example.MovieShop.ObjectsDto.Address.AddressDto;
import com.example.MovieShop.ObjectsDto.Address.AddressWithoutId;
import com.example.MovieShop.Services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@RequestMapping(path = "/address")
public class AddressController {
    private final AddressService addressService;
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Address createAddress(@RequestBody @Validated AddressDto addressDto) throws Exception{
        return addressService.createAddress(addressDto);
    }
    @PutMapping(path = "/{id}")
    public Address updateAddress(@RequestBody @Validated AddressDto addressDto, @PathVariable Long id) {
        //TODO while showint update address there shows UUID
        return addressService.updateAddress(addressDto, id);
    }
    @GetMapping
    public List<AddressWithoutId> getAllAddresses(){
        return addressService.getAllAddress();
    }
    @GetMapping(path = "/{id}")
    public AddressWithoutId getAddressById(@PathVariable Long id){
        return addressService.getAddress(id);
    }
    @DeleteMapping(path = "/{id}")
    public void deleteAddressById(@PathVariable Long id){
        addressService.deleteAddress(id);
    }
}
