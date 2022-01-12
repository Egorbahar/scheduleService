package com.egorbahar.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "schedule")
@Data
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    private Candidate candidate;
    @OneToOne
    private Employee employee;
    @OneToOne
    private Vacancy vacancy;
    @OneToOne
    private Interview interview;
}
