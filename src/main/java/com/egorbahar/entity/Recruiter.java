package com.egorbahar.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "recruiter")
@PrimaryKeyJoinColumn(name = "user_id")
public class Recruiter extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
