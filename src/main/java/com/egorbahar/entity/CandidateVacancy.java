package com.egorbahar.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "candidate_vacancy")
public class CandidateVacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "vacancy_id", nullable = false)
    private Vacancy vacancy;

    public CandidateVacancy(@NotNull Candidate candidate, @NotNull Vacancy vacancy) {
        this.candidate = candidate;
        this.vacancy = vacancy;
    }
}
