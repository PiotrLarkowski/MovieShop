package com.example.MovieShop.ObjectsDto;

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
