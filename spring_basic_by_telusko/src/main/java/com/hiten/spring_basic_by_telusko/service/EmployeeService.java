package com.hiten.spring_basic_by_telusko.service;

import com.hiten.spring_basic_by_telusko.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int empId);

    String createEmployee(Employee emp);

    String updateEmployee(Employee emp);

    String deleteEmployee(int empId);
}
