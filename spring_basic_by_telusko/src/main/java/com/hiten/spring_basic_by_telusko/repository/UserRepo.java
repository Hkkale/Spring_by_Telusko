package com.hiten.spring_basic_by_telusko.repository;

import com.hiten.spring_basic_by_telusko.model.Employee;
import com.hiten.spring_basic_by_telusko.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {
    public Users findByUserName(String username);
}
