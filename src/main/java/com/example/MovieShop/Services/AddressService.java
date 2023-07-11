package com.example.MovieShop.Services;

import com.example.MovieShop.Exceptions.Address.AddressNotFoundException;
import com.example.MovieShop.Objects.Client;
import com.example.MovieShop.ObjectsDto.Address.AddressDto;
import com.example.MovieShop.ObjectsDto.Address.AddressWithoutId;
import com.example.MovieShop.ObjectsDto.Client.ClientWithoutList;
import com.example.MovieShop.Repositorys.AddressRepository;
import com.example.MovieShop.Repositorys.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.MovieShop.Objects.Address;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class AddressService {
    private final AddressRepository addressRepository;
    private final ClientRepository clientRepository;

    public AddressService(AddressRepository addressRepository, ClientRepository clientRepository) {
        this.addressRepository = addressRepository;
        this.clientRepository = clientRepository;
    }

    public Address createAddress(AddressDto addressDto) {
        Address address = Address.builder()
                .addressUUID(UUID.randomUUID().toString())
                .city(addressDto.getCity())
                .street(addressDto.getStreet())
                .build();
        log.info("Address has been created");
        addressRepository.save(address);
        return address;
    }

    public void updateAddress(AddressDto addressDto, long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));
        address.setCity(address.getCity());
        address.setStreet(addressDto.getStreet());
        log.info("Address has been updated");
    }

    public List<AddressWithoutId> getAllAddress() {
        ArrayList<Address> addressList = new ArrayList<>();
        addressRepository.findAll().forEach(address -> addressList.add(address));
        List<AddressWithoutId> clientsWithoutList = addressList.stream().map(address -> AddressWithoutId.builder()
                .addressId(address.getAddressId())
                .street(address.getStreet())
                .city(address.getCity())
                .build()
        ).collect(Collectors.toList());
        log.info("Returning Addresses list");
        return clientsWithoutList;
    }

    public Address getAddressWithId(long id) {
        log.info("Returning address by id: " + id);
        return addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));
    }
    public AddressWithoutId getAddress(long id){
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));
        return AddressWithoutId.builder()
                .addressId(address.getAddressId())
                .city(address.getCity())
                .street(address.getStreet())
                .build();
    }

    public AddressWithoutId updateAddress(@RequestBody @Validated AddressDto addressDto, @PathVariable Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));
        address.setStreet(addressDto.getStreet());
        address.setCity(addressDto.getCity());
        return AddressWithoutId.builder()
                .addressId(address.getAddressId())
                .street(address.getStreet())
                .city(address.getCity())
                .build();
    }

    public void deleteAddress(long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));
        Iterable<Client> allClients = clientRepository.findAll();
        allClients.forEach(client -> {
            if (client.getAddress().getAddressUUID().equals(address.getAddressUUID())) {
                client.setAddress(null);
            }
        });
        log.info("Deleting Address by id: " + id);
        addressRepository.delete(address);
    }
}
