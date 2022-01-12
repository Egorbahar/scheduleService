package com.egorbahar.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "vacancy")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 3, max = 50, message = "{vacancy.name.size}")
    private String name;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;
    @NotNull
    @Size(min = 3, max = 50, message = "{vacancy.position.size}")
    private String position;
    @ManyToMany
    @JoinTable(name = "candidate_vacancy",
            joinColumns = @JoinColumn(name = "vacancy_id"),
            inverseJoinColumns = @JoinColumn(name = "candidate_id"))
    private Set<Candidate> candidates;
    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="vacancy_id")
    private Department department;
}
