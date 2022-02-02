package com.egorbahar.service.impl;

import com.egorbahar.component.LocalMessageSource;
import com.egorbahar.entity.CandidateVacancy;
import com.egorbahar.repository.CandidateVacancyRepository;
import com.egorbahar.service.CandidateVacancyService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class CandidateVacancyServiceImpl implements CandidateVacancyService {
    private final CandidateVacancyRepository candidateVacancyRepository;
    private final LocalMessageSource messageSource;

    @Override
    public CandidateVacancy save(CandidateVacancy candidateVacancy) {
        return candidateVacancyRepository.save(candidateVacancy);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        candidateVacancyRepository.deleteById(id);
    }

    @Override
    public CandidateVacancy update(CandidateVacancy candidateVacancy) {
        findById(candidateVacancy.getId());
        return candidateVacancyRepository.saveAndFlush(candidateVacancy);
    }

    @Override
    public List<CandidateVacancy> findAll() {
        return candidateVacancyRepository.findAll();
    }

    @Override
    public CandidateVacancy findById(Long id) {
        return candidateVacancyRepository.findById(id).orElseThrow(() -> new RuntimeException(messageSource.getMessage("error.candidateVacancy.notExist", new Object[]{})));
    }
}
