package com.egorbahar.entity;

import lombok.Data;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "department.name.notNull")
    @Size(min = 3, max = 50, message = "{department.name.size}")
    private String name;
    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="department_id")
    private Company company;
    @OneToMany (mappedBy="department", fetch=FetchType.EAGER)
    private Set<Vacancy> vacancies;
}
