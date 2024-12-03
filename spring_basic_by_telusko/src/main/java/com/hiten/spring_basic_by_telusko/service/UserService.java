package com.hiten.spring_basic_by_telusko.service;

import com.hiten.spring_basic_by_telusko.model.Users;
import com.hiten.spring_basic_by_telusko.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepo repo;
    AuthenticationManager authManager;

    JwtService jwtService;

    @Autowired
    public UserService(UserRepo repo,AuthenticationManager authManager ,JwtService jwtService){
        this.repo=repo;
        this.authManager=authManager;
        this.jwtService=jwtService;
    }
    public Users register(Users user) {
        return repo.save(user);
    }

    public String verify(Users user) {
        Authentication authentication=authManager.authenticate((new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword())));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUserName());
        }
        return "Fails";
    }
}
