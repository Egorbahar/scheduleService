package com.egorbahar.entity;

import com.egorbahar.enums.Position;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "vacancy")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 3, max = 50, message = "{vacancy.name.size}")
    private String name;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private Position position;
    @OneToMany(mappedBy = "vacancy", fetch = FetchType.LAZY)
    private Set<CandidateVacancy> candidateVacancies;
    @ManyToOne
    @JoinColumn (name="department_id")
    private Department department;
}
