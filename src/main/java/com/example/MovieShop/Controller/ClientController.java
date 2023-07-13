package com.example.MovieShop.Controller;

import com.example.MovieShop.Objects.Client;
import com.example.MovieShop.ObjectsDto.Client.ClientWithCountOfRent;
import com.example.MovieShop.ObjectsDto.Client.ClientWithoutAddressId;
import com.example.MovieShop.ObjectsDto.Client.ClientWithoutList;
import com.example.MovieShop.ObjectsDto.Client.ClientWithoutListIdAndAddress;
import com.example.MovieShop.Services.ClientService;
import com.example.MovieShop.Services.ClientWithoutId;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/client")
public class ClientController {
    private final ClientService clientService;
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @PostMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Client createClient(@RequestBody @Validated ClientWithoutListIdAndAddress clientWithoutListAndId){
        return clientService.createClient(clientWithoutListAndId);
    }
    @GetMapping
    public List<ClientWithoutList> getAllClients(){
        return clientService.getAllClients();
    }
    @GetMapping("/{id}")
    public ClientWithoutAddressId getClientById(@PathVariable Long id){
        return clientService.getClientWithoutListById(id);
    }

    @PutMapping("/name/{id}")
    public ClientWithoutList updateClientFirstAndLastName(@RequestBody @Validated ClientWithoutListIdAndAddress clientWithoutListIdAndAddress, @PathVariable Long id){
        return clientService.updateFirstNameandLastNameOfClient(clientWithoutListIdAndAddress, id);
    }
    @PutMapping("/IncreaseCountOfBuy/{clientId}")
    public ClientWithCountOfRent increaseClientCountOfBuysByOne(@PathVariable Long clientId){
        //TODO function work while client don't have rent movie list
         return clientService.addClientCountOfBuyByOne(clientId);
    }
    @PutMapping("/DecreaseCountOfBuy/{clientId}")
    public Client decreaseClientCountOfBuysByOne(@PathVariable Long clientId){
        return clientService.removeClientCountOfBuyByOne(clientId);
    }
    @PutMapping("/address/{addressId}/{clientId}")
    public Client updateClientAddress(@PathVariable Long addressId, @PathVariable Long clientId) {
        //TODO while add address to client address have UUID
        return clientService.updateClientAddress(addressId, clientId);
    }
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
    }
}
