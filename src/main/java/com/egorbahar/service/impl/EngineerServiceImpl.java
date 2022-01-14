package com.egorbahar.service.impl;

import com.egorbahar.component.LocalMessageSource;
import com.egorbahar.entity.Engineer;
import com.egorbahar.repository.EngineerRepository;
import com.egorbahar.service.EngineerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EngineerServiceImpl implements EngineerService {
    private final EngineerRepository engineerRepository;
    private final LocalMessageSource messageSource;

    @Override
    public void save(Engineer engineer) {
        engineerRepository.save(engineer);
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
}
