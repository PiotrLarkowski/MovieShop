package com.example.MovieShop.Services;

import com.example.MovieShop.Exceptions.Address.AddressNotFoundException;
import com.example.MovieShop.ObjectsDto.AddressDto;
import com.example.MovieShop.Repositorys.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.MovieShop.Objects.Address;

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
        addressRepository.save(address);
        return address;
    }
    public void updateAddress(AddressDto addressDto, long id){
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));
        address.setCity(address.getCity());
        address.setStreet(addressDto.getStreet());
    }
    public List<Address> getAllAddress(){
        ArrayList<Address> addressList = new ArrayList<>();
        addressRepository.findAll().forEach(address -> addressList.add(address));
        return addressList;
    }
    public Address getAddress(long id){
        return addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));
    }
    public void deleteAddress(long id){
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));
        addressRepository.delete(address);
    }
}
