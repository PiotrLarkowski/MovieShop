package com.example.MovieShop.ObjectsDto.Address;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AddressDto {
    private String city;
    private String street;
}
