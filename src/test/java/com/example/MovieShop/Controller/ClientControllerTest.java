package com.example.MovieShop.Controller;

import com.example.MovieShop.Objects.Client;
import com.example.MovieShop.ObjectsDto.Client.ClientWithoutListIdAndAddress;
import com.example.MovieShop.Services.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
    void getClientById() {

    }

    @Test
    void updateClientFirstAndLastName() {
    }

    @Test
    void updateClientAddress() {
    }

    @Test
    void deleteClient() {
    }
}