package com.egorbahar.service.impl;

import com.egorbahar.entity.Role;
import com.egorbahar.entity.User;
import com.egorbahar.repository.RoleRepository;
import com.egorbahar.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public User save(User user) {
        Role role = roleRepository.findByName("ROLE_USER");
        user.setRole(role);
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public User findByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    public User findByUserNameAndPassword(String userName, String password) {
        User user = findByUserName(userName);
        if (user != null) {
            if (passwordEncoder().matches(password, user.getPassword())) {
                return user;
            }
        }
        return null; //write exception
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
