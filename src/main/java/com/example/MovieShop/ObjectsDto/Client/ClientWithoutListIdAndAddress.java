package com.example.MovieShop.ObjectsDto.Client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ClientWithoutListIdAndAddress {
    private String clientFirstName;
    private String clientLastName;
}
