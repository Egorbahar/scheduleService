package com.egorbahar.service.impl;

import com.egorbahar.component.LocalMessageSource;
import com.egorbahar.entity.Candidate;
import com.egorbahar.repository.CandidateRepository;
import com.egorbahar.service.CandidateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final LocalMessageSource messageSource;

    @Override
    public Candidate save(Candidate candidate) {
      return candidateRepository.save(candidate);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        candidateRepository.deleteById(id);
    }

    @Override
    public Candidate update(Candidate candidate) {
        findById(candidate.getId());
        return candidateRepository.saveAndFlush(candidate);
    }

    @Override
    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    @Override
    public Candidate findById(Long id) {

        return candidateRepository.findById(id).orElseThrow(() -> new RuntimeException(messageSource.getMessage("error.candidate.notExist", new Object[]{})));
    }
}
