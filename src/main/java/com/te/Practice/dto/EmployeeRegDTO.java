package com.te.Practice.dto;

import com.te.Practice.entity.Address;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeRegDTO {

    private String empName;
    private Integer age;
    private List<AddressDTO> addressesDTO;
}
