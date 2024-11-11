package com.hiten.spring_basic_by_telusko.controller;

import com.hiten.spring_basic_by_telusko.model.Employee;
import com.hiten.spring_basic_by_telusko.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    EmployeeService service;


    @Autowired
    public EmployeeController(EmployeeService service){
        this.service=service;
    }



    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(){
        return new ResponseEntity<>(service.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/employees/{empId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int empId){
        return new ResponseEntity<>(service.getEmployeeById(empId), HttpStatus.ACCEPTED);
    }

    @PostMapping("/employees")
    public ResponseEntity<String> createEmployee( @RequestBody Employee emp){
        return new ResponseEntity<>(service.createEmployee(emp), HttpStatus.CREATED);
    }

    @PutMapping("/employees")
    public ResponseEntity<String> updateEmployee( @RequestBody Employee emp){
        return new ResponseEntity<>(service.updateEmployee(emp), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/employees/{empId}")
    public ResponseEntity<String> deleteEmployee( @PathVariable int empId){
        return new ResponseEntity<>(service.deleteEmployee(empId), HttpStatus.OK);
    }

}
