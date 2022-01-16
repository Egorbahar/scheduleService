package com.egorbahar.entity;

import com.egorbahar.enums.Position;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@Table(name = "engineer")
@PrimaryKeyJoinColumn(name = "user_id")
public class Engineer extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Position position;
    @OneToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
