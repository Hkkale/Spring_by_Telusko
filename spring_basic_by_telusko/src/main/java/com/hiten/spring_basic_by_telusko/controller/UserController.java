package com.hiten.spring_basic_by_telusko.controller;


import com.hiten.spring_basic_by_telusko.model.Users;
import com.hiten.spring_basic_by_telusko.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {

    UserService userService;
    BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    public UserController(UserService userService){
        this.userService=userService;
    }


    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userService.register(user);

    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        return userService.verify(user);

    }

}
