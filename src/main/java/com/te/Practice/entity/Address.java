package com.te.Practice.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Address {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer addId;
    private String city;
    private String state;

    @ManyToOne(cascade = CascadeType.ALL)
    private Employee employee;
}
