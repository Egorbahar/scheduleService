package com.egorbahar.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=5, message = "Не меньше 5 знаков")
    private String name;
    @Size(min=5, message = "Не меньше 5 знаков")
    private String surname;
    @Size(min=5, message = "Не меньше 5 знаков")
    private String email;
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private Set<CandidateVacancy> candidateVacancies;
    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
