package com.example.MovieShop.Controller;

import com.example.MovieShop.Objects.Client;
import com.example.MovieShop.ObjectsDto.ActorDto;
import com.example.MovieShop.ObjectsDto.AddressDto;
import com.example.MovieShop.ObjectsDto.ClientDto;
import com.example.MovieShop.Services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/Client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Client createClient(@RequestBody @Validated ClientDto clientDto){
        return clientService.createClient(clientDto);
    }

    @GetMapping
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }
    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id){
        return clientService.getClientById(id);
    }
    @PutMapping("/name")
    public Client updateClientFirstAndLastName(@RequestBody @Validated ClientDto clientDto){
        return clientService.updateFirstNameandLastNameOfClient(clientDto);
    }
    @PutMapping("/IncreaseCountOfBuy")
    public Client increaseClientCountOfBuysByOne(@RequestBody @Validated Client client){
         return clientService.addClientCountOfBuyByOne(client);
    }
    @PutMapping("/DecreaseCountOfBuy")
    public Client decreaseClientCountOfBuysByOne(@RequestBody @Validated Client client){
        return clientService.removeClientCountOfBuyByOne(client);
    }
    @PutMapping("/address/{id}")
    public Client updateClientAddress(@RequestBody @Validated AddressDto addressDto, @PathVariable Long id) {
        Client clientById = clientService.getClientById(id);
        return clientService.updateClientAddress(addressDto, clientById);
    }
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
    }
}
