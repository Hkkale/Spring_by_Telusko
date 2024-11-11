package com.hiten.spring_basic_by_telusko.service;

import com.hiten.spring_basic_by_telusko.model.Employee;
import com.hiten.spring_basic_by_telusko.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    EmployeeRepository employeeRepository;


    @Autowired
    private EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int empId) {
        return employeeRepository.findById(empId).orElse(new Employee(0,"not found","NA","NA",0));
    }

    @Override
    public String createEmployee(Employee emp) {
        employeeRepository.save(emp);
        return "Created Successfully.";
    }

    @Override
    public String updateEmployee(Employee emp) {
        employeeRepository.save(emp);
        return "Updated Successfully.";
    }

    @Override
    public String deleteEmployee(int empId) {
        employeeRepository.deleteById(empId);
        return "Deleted Successfully.";
    }
}
