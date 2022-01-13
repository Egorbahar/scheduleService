package com.egorbahar.repository;

import com.egorbahar.entity.CandidateVacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateVacancyRepository extends JpaRepository<CandidateVacancy,Long> {
}
