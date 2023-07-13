package com.example.MovieShop.ObjectsDto.Client;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientWithCountOfRent {
    private Long clientId;
    private int clientCountOfBuy;
    private String clientFirstName;
    private String clientLastName;
}
