package com.example.MovieShop.Controller;

import com.example.MovieShop.Objects.Client;
import com.example.MovieShop.ObjectsDto.Client.ClientWithoutList;
import com.example.MovieShop.ObjectsDto.Client.ClientWithoutListIdAndAddress;
import com.example.MovieShop.Services.ClientService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

class ClientControllerTest {
    @Test
    void shouldCreateNewClient() {
        //given
        ClientService clientService = mock(ClientService.class);
        given(clientService.createClient(Mockito.any(ClientWithoutListIdAndAddress.class)))
                .willReturn(Client.builder()
                        .clientId(0L)
                        .clientFirstName("Test Client First Name")
                        .clientLastName("Test Client Last Name")
                        .clientCountOfRent(0)
                        .address(null)
                        .build());
        //when
        clientService.createClient(new ClientWithoutListIdAndAddress());
        //then
        assertEquals(clientService.createClient(new ClientWithoutListIdAndAddress())
                        .getClientFirstName(),"Test Client First Name");
    }

    @Test
    void shouldGetAllClient() {
        //given
        ClientService clientService = mock(ClientService.class);
        given(clientService.getAllClients()).willReturn(getClientsList());
        //when
        List<ClientWithoutList> clients = clientService.getAllClients();
        //then
        assertEquals(clients, Matchers.notNullValue());
        assertEquals(clients, Matchers.hasSize(3));
    }

    private List<ClientWithoutList> getClientsList(){
        List<ClientWithoutList> clients = new ArrayList<>();
        clients.add(new ClientWithoutList(1L,"","",0,null,null));
        clients.add(new ClientWithoutList(2L,"","",0,null,null));
        clients.add(new ClientWithoutList(3L,"","",0,null,null));
        return clients;
    }
}