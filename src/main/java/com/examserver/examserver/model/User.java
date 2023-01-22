package com.examserver.examserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Username should not be empty")
    @Size(min = 3, max = 35, message = "Name should be between 3 and 35 characters")
    private String username;

    @NotEmpty(message = "Password should not be empty")
    private String password;

    @NotEmpty(message = "firstName should not be empty")
    private String firstName;

    @NotEmpty(message = "lastName should not be empty")
    private String lastName;

    @Email
    @NotEmpty(message = "Email should not be empty")
    private String email;

    private String phone;

    private boolean enabled = true;

    private String profile;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> set = new HashSet<>();

        this.userRoles.forEach(userRoles -> {
            set.add(new Authority(userRoles.getRole().getRoleName()));
        });
        
        return set;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
