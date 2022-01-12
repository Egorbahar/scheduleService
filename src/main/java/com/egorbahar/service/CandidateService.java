package com.egorbahar.service;

import com.egorbahar.entity.Candidate;

import java.util.List;

public interface CandidateService {
    void save(Candidate candidate);

    void deleteById(Long id);

    Candidate update(Candidate candidate);

    List<Candidate> findAll();

    Candidate findById(Long id);
}
