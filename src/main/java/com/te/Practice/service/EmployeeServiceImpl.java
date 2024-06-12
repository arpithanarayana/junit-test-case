package com.te.Practice.service;

import com.te.Practice.dto.AddressDTO;
import com.te.Practice.dto.EmployeeDTO;
import com.te.Practice.dto.EmployeeRegDTO;
import com.te.Practice.entity.Address;
import com.te.Practice.entity.Employee;
import com.te.Practice.repository.AddressRepository;
import com.te.Practice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;

    @Override
    public EmployeeRegDTO saveEmployees(EmployeeRegDTO employeeRegDTO) {

        Employee employee = Employee.builder()
                .empName(employeeRegDTO.getEmpName())
                .age(employeeRegDTO.getAge())
                .build();

        List<Address> addresses = employeeRegDTO.getAddressesDTO().stream().map(e -> Address.builder()
                .city(e.getCity())
                .state(e.getState())
                .employee(employee)
                .build()).collect(Collectors.toList());

        employee.setAddresses(addresses);
        addresses.forEach(e->e.setEmployee(employee));

        employeeRepository.save(employee);

        return employeeRegDTO;
    }

    @Override
    public EmployeeRegDTO getEmployee(Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        EmployeeRegDTO employeeRegDTO = EmployeeRegDTO.builder()
                .empName(employee.getEmpName())
                .age(employee.getAge())
                .addressesDTO(employee.getAddresses()
                        .stream().map(a->
                                          AddressDTO.builder()
                                          .city(a.getCity())
                                          .state(a.getState())
                                          .build()).collect(Collectors.toList()))
                .build();
        return employeeRegDTO;
    }

    @Override
    public List<EmployeeRegDTO> getAllEmployees() {
        return employeeRepository.findAll().stream().map(e->EmployeeRegDTO.builder()
                                           .empName(e.getEmpName())
                                           .age(e.getAge())
                                           .addressesDTO(e.getAddresses().stream()
                                                    .map(a->AddressDTO.builder()
                                                    .city(a.getCity())
                                                    .state(a.getState())
                                                    .build())
                                                    .collect(Collectors.toList()))
                                           .build())
                                           .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployee(Integer employeeId, Integer age, String empName) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(null);
        employee.setEmpName(empName);
        employee.setAge(age);

        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .empId(employee.getEmpId())
                .age(employee.getAge())
                .empName(employee.getEmpName())
                .build();
        return employeeDTO;
    }

    @Override
    public Integer deleteEmployee(Integer employeeId) {
        employeeRepository.deleteById(employeeId);
        return employeeId;
    }


}
