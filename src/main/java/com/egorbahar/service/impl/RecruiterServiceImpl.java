package com.egorbahar.service.impl;

import com.egorbahar.component.LocalMessageSource;
import com.egorbahar.entity.Recruiter;
import com.egorbahar.repository.RecruiterRepository;
import com.egorbahar.service.RecruiterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecruiterServiceImpl implements RecruiterService {
    private final RecruiterRepository recruiterRepository;
    private final LocalMessageSource messageSource;

    @Override
    public Recruiter save(Recruiter recruiter) {
        return recruiterRepository.save(recruiter);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        recruiterRepository.deleteById(id);
    }

    @Override
    public Recruiter update(Recruiter recruiter) {
        findById(recruiter.getId());
        return recruiterRepository.saveAndFlush(recruiter);
    }

    @Override
    public List<Recruiter> findAll() {
        return recruiterRepository.findAll();
    }

    @Override
    public Recruiter findById(Long id) {
        return recruiterRepository.findById(id).orElseThrow(()->new RuntimeException(messageSource.getMessage("error.recruiter.notExist", new Object[]{})));
    }
}
