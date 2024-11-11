package com.hiten.spring_basic_by_telusko.repository;

import com.hiten.spring_basic_by_telusko.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
