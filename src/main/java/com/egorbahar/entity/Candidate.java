package com.egorbahar.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
@Table(name = "candidate")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=5, message = "Не меньше 5 знаков")
    private String name;
    @Size(min=5, message = "Не меньше 5 знаков")
    private String surname;
    @ManyToMany
    @JoinTable(name = "candidate_vacancy",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "vacancy_id")) //one to one переделать
    private Set<Vacancy> vacancies;
    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="candidate_id", insertable = false, updatable = false)
    private Company company;
    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="candidate_id")
    private Employee employee;
}
