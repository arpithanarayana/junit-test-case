package com.te.Practice.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeDTO {

    private Integer empId;
    private String empName;
    private Integer age;
}
