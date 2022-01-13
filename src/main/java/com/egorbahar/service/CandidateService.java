package com.egorbahar.service;

import com.egorbahar.entity.Candidate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CandidateService {
    void save(Candidate candidate);

    void deleteById(Long id);

    Candidate update(Candidate candidate);

    List<Candidate> findAll();

    Candidate findById(Long id);
}
