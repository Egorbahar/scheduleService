package com.egorbahar.service;

import com.egorbahar.entity.Vacancy;

import java.util.List;

public interface VacancyService {
    Vacancy save(Vacancy vacancy);

    void deleteById(Long id);

    Vacancy update(Vacancy vacancy);

    List<Vacancy> findAll();

    Vacancy findById(Long id);

    List<Vacancy> findAllById(List<Long> longList);
}
