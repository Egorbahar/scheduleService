package com.egorbahar.repository;

import com.egorbahar.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;


@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {
}
