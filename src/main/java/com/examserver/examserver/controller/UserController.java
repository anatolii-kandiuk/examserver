package com.examserver.examserver.controller;

import com.examserver.examserver.model.Role;
import com.examserver.examserver.model.User;
import com.examserver.examserver.model.UserRole;
import com.examserver.examserver.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    // create user
    @PostMapping("/")
    public User createUser(@RequestBody @Valid User user, 
                            BindingResult bindingResult) throws Exception {

        if(bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());;
        }
        user.setProfile("default.png");
        Set<UserRole> roles = new HashSet<>();

        Role role = new Role();
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);

        return this.userService.createUser(user, roles);
    }

    // get user by username
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username) {
        return this.userService.getUser(username);
    }

    // delete user by id
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        this.userService.deleteUser(userId);
    }

/*
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserNotFoundException ex) { return ResponseEntity }
*/

}
