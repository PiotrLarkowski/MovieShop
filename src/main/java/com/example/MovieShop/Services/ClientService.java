package com.example.MovieShop.Services;

import com.example.MovieShop.Exceptions.Client.ClientNotFoundException;
import com.example.MovieShop.Objects.Address;
import com.example.MovieShop.Objects.Client;
import com.example.MovieShop.ObjectsDto.Client.ClientWithoutList;
import com.example.MovieShop.ObjectsDto.Client.ClientWithoutListIdAndAddress;
import com.example.MovieShop.Repositorys.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class ClientService {
    private final ClientRepository clientRepository;
    private final AddressService addressService;

    public ClientService(ClientRepository clientRepository, AddressService addressService) {
        this.clientRepository = clientRepository;
        this.addressService = addressService;
    }

    public Client createClient(ClientWithoutListIdAndAddress clientWithoutListAndId){
        Client client = Client.builder()
                .clientFirstName(clientWithoutListAndId.getClientFirstName())
                .clientLastName(clientWithoutListAndId.getClientLastName())
                .clientCountOfBuy(0)
                .clientListOfMoviesRentByClient(new ArrayList<>())
                .build();
        clientRepository.save(client);
        log.info("Client has been created");
        return client;
    }
    public List<ClientWithoutList> getAllClients(){
        ArrayList<Client> listOfClient = new ArrayList<>();
        clientRepository.findAll().forEach(listOfClient::add);
        List<ClientWithoutList> clientsWithoutList = listOfClient.stream().map(client -> ClientWithoutList.builder()
                .clientId(client.getClientId())
                .clientFirstName(client.getClientFirstName())
                .clientLastName(client.getClientLastName())
                .address(client.getAddress())
                .build()
        ).collect(Collectors.toList());
        log.info("Return Clients list");
        return clientsWithoutList;
    }
    public ClientWithoutList getClientWithoutListById(Long id){
        log.info("Return Client by Id");
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        ClientWithoutList clientWithoutList = ClientWithoutList.builder()
                .clientId(client.getClientId())
                .clientFirstName(client.getClientFirstName())
                .clientLastName(client.getClientLastName())
                .address(client.getAddress())
                .build();
        return clientWithoutList;
    }
    private Client getClientById(Long id){
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
    }
    public Client updateFirstNameandLastNameOfClient(ClientWithoutListIdAndAddress clientWithoutListIdAndAddress, Long id){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
        client.setClientFirstName(clientWithoutListIdAndAddress.getClientFirstName());
        client.setClientLastName(clientWithoutListIdAndAddress.getClientLastName());
        log.info("Update client name");
        return client;
    }

    public Client addClientCountOfBuyByOne(Long clientId){
        Client client = getClientById(clientId);
        client.setClientCountOfBuy(client.getClientCountOfBuy() + 1);
        log.info("Add client buy count");
        return client;
    }
    public Client removeClientCountOfBuyByOne(Long clientId){
        Client client = getClientById(clientId);
        client.setClientCountOfBuy(client.getClientCountOfBuy() - 1);
        log.info("Remove client buy count ");
        return client;
    }
    public Client updateClientAddress(Long addressId, Long clientId){
        Client client = getClientById(clientId);
        Address address = addressService.getAddressWithId(addressId);
        client.setAddress(address);
        log.info("Add client address");
        return client;
    }
    public void deleteClient(Long id){
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        client.setAddress(null);
        clientRepository.delete(client);
        log.info("Delete client");
    }
}
