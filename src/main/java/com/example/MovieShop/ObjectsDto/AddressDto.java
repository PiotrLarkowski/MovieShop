package com.example.MovieShop.ObjectsDto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AddressDto {
    private Long addressId;
    @NotNull(message = "Name cannot be null")
    private String city;
    @NotNull(message = "Name cannot be null")
    private String street;
}
