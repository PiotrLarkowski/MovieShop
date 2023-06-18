package com.example.MovieShop.Objects;

import lombok.*;

import javax.persistence.*;
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private Long addressForeignId;
    private String city;
    private String street;
}
