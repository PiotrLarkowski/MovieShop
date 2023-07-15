package com.example.MovieShop.ObjectsDto.Client;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientWithoutListIdAndAddress {
    private String clientFirstName;
    private String clientLastName;
}
