package com.te.Practice.service;

import com.te.Practice.dto.AddressDTO;
import com.te.Practice.dto.EmployeeRegDTO;
import com.te.Practice.entity.Address;
import com.te.Practice.entity.Employee;
import com.te.Practice.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;
    private Employee employee;
    private Address address;
    private EmployeeRegDTO employeeRegDTO;
    private AddressDTO addressDTO;

    @BeforeEach
    void setUp1(){
        addressDTO=new AddressDTO("Gadag","Karnataka");
        employeeRegDTO=new EmployeeRegDTO("Ravi",29,List.of(addressDTO));
    }

    @Test
    void saveEmployees() {
        employeeService.saveEmployees(employeeRegDTO);
        verify(employeeRepository).save(Mockito.any(Employee.class));
    }

    @BeforeEach
    void setUp(){
        address = new Address(1, "Mandya", "Bangalore", null);
        employee = new Employee(101, "Allen", 34, List.of(address));
        address.setEmployee(employee);
    }
    
    @Test
    void getEmployee() {
        when(employeeRepository.findById(101)).thenReturn(Optional.of(employee));
        assertThat(employeeService.getEmployee(101).getEmpName())
                .isEqualTo(employee.getEmpName());
    }

    @Test
    void getAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(new ArrayList<Employee>());
        assertThat(employeeService.getAllEmployees()).isEmpty();
    }

    @Test
    void updateEmployee() {

    }

    @Test
    void deleteEmployee() {
        doNothing().when(employeeRepository).deleteById(any());
        assertThat(employeeService.deleteEmployee(101)).isEqualTo(101);
    }
}