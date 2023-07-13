package com.example.MovieShop.Services;

import com.example.MovieShop.Exceptions.Client.ClientNotFoundException;
import com.example.MovieShop.Objects.Address;
import com.example.MovieShop.Objects.Client;
import com.example.MovieShop.ObjectsDto.Address.AddressWithoutId;
import com.example.MovieShop.ObjectsDto.Client.ClientWithCountOfRent;
import com.example.MovieShop.ObjectsDto.Client.ClientWithoutAddressId;
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
                .clientCountOfRent(0)
                .clientListOfMoviesRentByClient(new ArrayList<>())
                .build();
        clientRepository.save(client);
        log.info("Client has been created");
        return client;
    }
    public List<ClientWithoutList> getAllClients(){
        ArrayList<Client> listOfClient = new ArrayList<>();
        clientRepository.findAll().forEach(listOfClient::add);
        List<ClientWithoutList> clientWithoutList = listOfClient.stream().map(client -> ClientWithoutList.builder()
                .clientId(client.getClientId())
                .clientFirstName(client.getClientFirstName())
                .clientLastName(client.getClientLastName())
                .clientCountOfRent(client.getClientCountOfRent())
                .clientTitleListOfMoviesRentByClient(new ArrayList<>())
                .address(client.getAddress())
                .build()).collect(Collectors.toList());
        for (int i = 0; i < clientWithoutList.size(); i++) {
            for (int j = 0; j < listOfClient.get(i).getClientListOfMoviesRentByClient().size(); j++) {
                clientWithoutList.get(i).addTitleToMovieList(listOfClient.get(i).getClientListOfMoviesRentByClient().get(i).getTitle());
            }
        }
        log.info("Return Clients list");
        return clientWithoutList;
    }
    public ClientWithoutAddressId getClientWithoutListById(Long id){
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        ClientWithoutList clientWithoutList = ClientWithoutList.builder()
                .clientId(client.getClientId())
                .clientFirstName(client.getClientFirstName())
                .clientLastName(client.getClientLastName())
                .clientCountOfRent(client.getClientCountOfRent())
                .address(client.getAddress())
                .build();
        List<String> titleList = new ArrayList<>();
        for (int i = 0; i < client.getClientListOfMoviesRentByClient().size(); i++) {
            titleList.add(client.getClientListOfMoviesRentByClient().get(i).getTitle());
        }
        ClientWithoutAddressId clientWithoutAddressId = ClientWithoutAddressId.builder()
                .clientId(clientWithoutList.getClientId())
                .clientFirstName(clientWithoutList.getClientFirstName())
                .clientLastName(clientWithoutList.getClientLastName())
                .clientTitleListOfMoviesRentByClient(titleList)
                .clientCountOfRent(client.getClientCountOfRent())
                .addressWithoutId(AddressWithoutId.builder()
                        .addressId(clientWithoutList.getAddress().getAddressId())
                        .city(clientWithoutList.getAddress().getCity())
                        .street(clientWithoutList.getAddress().getStreet())
                        .build())
                .build();
        log.info("Return Client by Id");
        return clientWithoutAddressId;
    }
    private Client getClientById(Long id){
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
    }
    public ClientWithoutList updateFirstNameandLastNameOfClient(ClientWithoutListIdAndAddress clientWithoutListIdAndAddress, Long id){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
        List<String> movieTitles = new ArrayList<>();
        client.getClientListOfMoviesRentByClient().forEach(Movie -> movieTitles.add(Movie.getTitle()));
        ClientWithoutList clientWithoutList = ClientWithoutList.builder()
                .clientId(client.getClientId())
                .clientFirstName(clientWithoutListIdAndAddress.getClientFirstName())
                .clientLastName(clientWithoutListIdAndAddress.getClientLastName())
                .clientTitleListOfMoviesRentByClient(movieTitles)
                .address(client.getAddress())
                .build();
        log.info("Update client name");
        return clientWithoutList;
    }

    public ClientWithCountOfRent addClientCountOfBuyByOne(Long clientId){
        Client client = getClientById(clientId);
        client.addClientCountOfRent();
        ClientWithCountOfRent clientWithCountOfRent = ClientWithCountOfRent.builder()
                .clientId(client.getClientId())
                .clientFirstName(client.getClientFirstName())
                .clientLastName(client.getClientLastName())
                .clientCountOfBuy(client.getClientCountOfRent())
                .build();
        log.info("Add client buy count");
        return clientWithCountOfRent;
    }
    public ClientWithCountOfRent lowerClientCountOfBuyByOne(Long clientId){
        Client client = getClientById(clientId);
        client.loverClientCountOfRent();
        ClientWithCountOfRent clientWithCountOfRent = ClientWithCountOfRent.builder()
                .clientId(client.getClientId())
                .clientFirstName(client.getClientFirstName())
                .clientLastName(client.getClientLastName())
                .clientCountOfBuy(client.getClientCountOfRent())
                .build();
        log.info("Lover client buy count ");
        return clientWithCountOfRent;
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
