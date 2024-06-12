package com.te.Practice.controller;

import com.te.Practice.dto.AddressDTO;
import com.te.Practice.dto.EmployeeDTO;
import com.te.Practice.dto.EmployeeRegDTO;
import com.te.Practice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(path="app")
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(path="dummy")
    public EmployeeRegDTO dummy(){
        return EmployeeRegDTO.builder()
                .empName("Allen")
                .age(24)
                .addressesDTO(List.of(AddressDTO.builder()
                        .city("Bangalore")
                        .state("Karnataka")
                        .build()))
                .build();
    }

    @PostMapping(path="save")
    public ResponseEntity<EmployeeRegDTO> saveEmployee(@RequestBody EmployeeRegDTO employeeRegDTO){
        EmployeeRegDTO emp=employeeService.saveEmployees(employeeRegDTO);
        if(emp!=null){
            return ResponseEntity.ok(emp);
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @GetMapping(path="get/{employeeId}")
    public ResponseEntity<EmployeeRegDTO> getEmployee(@PathVariable Integer employeeId){
        EmployeeRegDTO employee = employeeService.getEmployee(employeeId);
        if(employee!=null){
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "getAll")
    public ResponseEntity<List<EmployeeRegDTO>> getAllEmployees(){
        List<EmployeeRegDTO> employeeRegDTO=employeeService.getAllEmployees();
        if(employeeRegDTO!=null){
            return ResponseEntity.ok(employeeRegDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping(path="update/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Integer employeeId, @RequestParam(name="age")Integer age,
                                                      @RequestParam(name="empName")String empName){
        EmployeeDTO employeeDTO=employeeService.updateEmployee(employeeId,age,empName);
        if(employeeDTO!=null){
            return ResponseEntity.ok(employeeDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping(path="delete/{employeeId}")
    public ResponseEntity<Integer> deleteEmployee(@PathVariable Integer employeeId){
        Integer empId=employeeService.deleteEmployee(employeeId);
        if(empId!=null){
            return ResponseEntity.ok(empId);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
