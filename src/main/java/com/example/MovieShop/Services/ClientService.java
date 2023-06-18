package com.example.MovieShop.Services;

import com.example.MovieShop.Exceptions.Client.ClientNotFoundException;
import com.example.MovieShop.Objects.Address;
import com.example.MovieShop.Objects.Client;
import com.example.MovieShop.ObjectsDto.AddressDto;
import com.example.MovieShop.ObjectsDto.ClientDto;
import com.example.MovieShop.Repositorys.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    public Client createClient(ClientDto clientDto){
        Client client = Client.builder()
                .clientFirstName(clientDto.getClientFirstName())
                .clientLastName(clientDto.getClientLastName())
                .clientCountOfBuy(0)
                .clientListOfMoviesRentByClient(new ArrayList<>())
                .build();
        clientRepository.save(client);
        log.info("Client has been created");
        return client;
    }
    public List<Client> getAllClients(){
        ArrayList<Client> listOfClient = new ArrayList();
        clientRepository.findAll().forEach(client -> listOfClient.add(client));
        return listOfClient;
    }
    public Client getClientById(Long id){
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
    }
    public Client updateFirstNameandLastNameOfClient(ClientDto clientDto){
        Client client = clientRepository.findById(clientDto.getClientId())
                .orElseThrow(() -> new ClientNotFoundException(clientDto.getClientId()));
        client.setClientFirstName(clientDto.getClientFirstName());
        client.setClientLastName(client.getClientLastName());
        return client;
    }
    public Client updateClientCountOfBuy(Client client, int countOfBuy){
        client.setClientCountOfBuy(countOfBuy);
        return client;
    }
    public Client updateClientAddress(AddressDto addressDto, Client client){
        Address address = Address.builder()
                .city(addressDto.getCity())
                .street(addressDto.getStreet())
                .build();
        client.setAddress(address);
        return client;
    }
    public void deleteClient(Client client){
        clientRepository.delete(client);
    }
}
