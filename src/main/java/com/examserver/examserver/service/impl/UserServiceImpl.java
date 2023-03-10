package com.examserver.examserver.service.impl;

import com.examserver.examserver.helper.UserFoundException;
import com.examserver.examserver.model.User;
import com.examserver.examserver.model.UserRole;
import com.examserver.examserver.repository.RoleRepository;
import com.examserver.examserver.repository.UserRepository;
import com.examserver.examserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User local = this.userRepository.findByUsername(user.getUsername());

        if (local != null) {
            throw new UserFoundException();
        } else {
            //user create
            for (UserRole ur:userRoles) {
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);
        }

        return local;
    }

    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

}
