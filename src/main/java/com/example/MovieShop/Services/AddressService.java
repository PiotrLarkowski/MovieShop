package com.example.MovieShop.Services;

import com.example.MovieShop.Exceptions.Address.AddressNotFoundException;
import com.example.MovieShop.ObjectsDto.AddressDto;
import com.example.MovieShop.Repositorys.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.MovieShop.Objects.Address;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

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
        log.info("Address has been created");
        addressRepository.save(address);
        return address;
    }
    public void updateAddress(AddressDto addressDto, long id){
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));
        address.setCity(address.getCity());
        address.setStreet(addressDto.getStreet());
        log.info("Address has been updated");
    }
    public List<Address> getAllAddress(){
        ArrayList<Address> addressList = new ArrayList<>();
        addressRepository.findAll().forEach(address -> addressList.add(address));
        log.info("Returning Addresses list");
        return addressList;
    }
    public Address getAddress(long id){
        log.info("Returning address by id: "+id);
        return addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));
    }
    public Address updateAddress(@RequestBody @Validated AddressDto addressDto, @PathVariable Long id){
        Address address = addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));
        address.setStreet(addressDto.getStreet());
        address.setCity(address.getCity());
        return address;
    }
    public void deleteAddress(long id){
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));
        log.info("Deleting Address by id: "+id);
        addressRepository.delete(address);
    }
}
