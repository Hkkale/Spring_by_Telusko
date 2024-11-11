package com.hiten.spring_basic_by_telusko.service;

import com.hiten.spring_basic_by_telusko.model.Users;
import com.hiten.spring_basic_by_telusko.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepo repo;


    @Autowired
    public UserDetailsServiceImpl(UserRepo repo){
        this.repo=repo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user=repo.findByUserName(username);

        if(user==null){
            System.out.println("User not Found");
            throw new UsernameNotFoundException("User not Found");
        }

        return new UserPrincipal(user);
    }
}
