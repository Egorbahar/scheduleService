package com.egorbahar.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "company.name.notNull")
    @Size(min = 3, max = 50, message = "company.name.size")
    private String name;
    @NotNull(message = "company.address.notNull")
    private String address;
    @OneToMany (mappedBy="company", fetch=FetchType.EAGER)
    private Set<Candidate> candidates;
    @OneToMany (mappedBy="company", fetch=FetchType.EAGER)
    private Set<Department> departments;
    @OneToMany (mappedBy="company", fetch=FetchType.EAGER)
    private Set<Employee> recruiters;
}
