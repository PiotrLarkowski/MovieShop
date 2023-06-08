package com.example.MovieShop.Services;

import com.example.MovieShop.Exceptions.AddressNotFoundException;
import com.example.MovieShop.ObjectsDto.AddressDto;
import com.example.MovieShop.Repositorys.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.MovieShop.Objects.Address;

@Slf4j
@Service
@Transactional
public class AddressService{
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address createAddress(AddressDto addressDto){
        Address address = Address.builder()
                .addressId(addressDto.getAddressId())
                .city(addressDto.getCity())
                .street(addressDto.getStreet())
                .build();
        addressRepository.save(address);
        return address;
    }
    public void updateAddress(AddressDto addressDto, long id){
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));
        address.setCity(address.getCity());
        address.setStreet(addressDto.getStreet());
    }
}
