package com.egorbahar.service;

import com.egorbahar.entity.CandidateVacancy;

import java.util.List;

public interface CandidateVacancyService {

    CandidateVacancy save(CandidateVacancy candidateVacancy);

    void deleteById(Long id);

    CandidateVacancy update(CandidateVacancy candidateVacancy);

    List<CandidateVacancy> findAll();

    CandidateVacancy findById(Long id);
}
