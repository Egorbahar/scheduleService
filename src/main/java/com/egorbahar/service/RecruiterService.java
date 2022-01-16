package com.egorbahar.service;

import com.egorbahar.entity.Recruiter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecruiterService {
    Recruiter save(Recruiter recruiter);

    void deleteById(Long id);

    Recruiter update(Recruiter recruiter);

    List<Recruiter> findAll();

    Recruiter findById(Long id);
}
