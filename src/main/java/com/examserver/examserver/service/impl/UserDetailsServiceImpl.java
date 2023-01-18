package com.examserver.examserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.examserver.examserver.model.User;
import com.examserver.examserver.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = this.userRepository.findByUsername(username);
        
        if(user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User does not found !!!");
        }

        return user;
    }

}