package com.egorbahar.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedule")
@Data
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startTime;
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToOne
    @JoinColumn(name = "recruiter_id")
    private Recruiter recruiter;
    @OneToOne
    @JoinColumn(name = "engineer_id")
    private Engineer engineer;
    @OneToOne
    @JoinColumn(name = "candidate_vacancy_id")
    private CandidateVacancy candidateVacancy;
}
