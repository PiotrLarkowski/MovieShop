package com.example.MovieShop.ObjectsDto.Address;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AddressWithoutId {
    private Long addressId;
    private String city;
    private String street;
}
