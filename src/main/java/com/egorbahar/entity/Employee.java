package com.egorbahar.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "employee")
@NoArgsConstructor
@ConfigurationProperties(prefix = "validationMessages.properties")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "employee.name.notNull")
    @Size(min = 3, max = 50, message = "employee.name.size")
    private String name;
    @NotNull
    @Size(min=3, max = 50, message = "employee.surname.size")
    private String surname;
    @OneToMany (mappedBy="employee", fetch=FetchType.EAGER)
    private Set<Candidate> candidates;
    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="employee_id")
    private Company company;
}
