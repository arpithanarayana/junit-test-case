package com.te.Practice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Employee {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer empId;
    private String empName;
    private Integer age;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<Address> addresses;
}
