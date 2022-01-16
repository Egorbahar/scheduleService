package com.egorbahar.service.impl;

import com.egorbahar.component.LocalMessageSource;
import com.egorbahar.entity.Engineer;
import com.egorbahar.entity.Role;
import com.egorbahar.repository.EngineerRepository;
import com.egorbahar.repository.RoleRepository;
import com.egorbahar.service.EngineerService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EngineerServiceImpl implements EngineerService {
    private final EngineerRepository engineerRepository;
    private final RoleRepository roleRepository;
    private final LocalMessageSource messageSource;

    @Override
    public Engineer save(Engineer engineer) {
        Role role = roleRepository.findByName("ROLE_ENGINEER");
        engineer.setRole(role);
        engineer.setPassword(passwordEncoder().encode(engineer.getPassword()));
        return engineerRepository.save(engineer);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        engineerRepository.deleteById(id);
    }

    @Override
    public Engineer update(Engineer engineer) {
        findById(engineer.getId());
        return engineerRepository.saveAndFlush(engineer);
    }

    @Override
    public List<Engineer> findAll() {
        return engineerRepository.findAll();
    }

    @Override
    public Engineer findById(Long id) {
        return engineerRepository.findById(id).orElseThrow(() -> new RuntimeException(messageSource.getMessage("error.engineer.notExist", new Object[]{})));
    }
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
