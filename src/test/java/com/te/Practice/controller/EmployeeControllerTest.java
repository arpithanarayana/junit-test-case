package com.te.Practice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.Practice.dto.AddressDTO;
import com.te.Practice.dto.EmployeeRegDTO;
import com.te.Practice.entity.Address;
import com.te.Practice.entity.Employee;
import com.te.Practice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private EmployeeRegDTO employeeRegDTO;
    private AddressDTO addressDTO;
    private Employee employee;
    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address(1, "Mandya", "Bangalore", null);
        employee = new Employee(101, "Allen", 34, List.of(address));
        address.setEmployee(employee);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveEmployee() {

    }

    @BeforeEach
    void setUp1() {
        addressDTO=new AddressDTO("Mandya", "Bangalore");
        employeeRegDTO=new EmployeeRegDTO("Allen", 34,List.of(addressDTO));
    }

    @Test
    void getEmployee() throws Exception {
        when(employeeService.getEmployee(employee.getEmpId())).thenReturn(employeeRegDTO);

        ResponseEntity<EmployeeRegDTO> responseEntity = employeeController.getEmployee(employee.getEmpId());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(employeeRegDTO, responseEntity.getBody());
        System.out.println(employeeRegDTO.getEmpName());
    }

    @Test
    void getAllEmployees() {
        when(employeeService.getAllEmployees()).thenReturn(List.of(employeeRegDTO));
        ResponseEntity<List<EmployeeRegDTO>> responseEntity = employeeController.getAllEmployees();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(List.of(employeeRegDTO), responseEntity.getBody());
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void deleteEmployee() {
    }
}