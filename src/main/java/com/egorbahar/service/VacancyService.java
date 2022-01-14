package com.egorbahar.service;

import com.egorbahar.entity.Vacancy;

import java.util.List;

public interface VacancyService {
    void save(Vacancy vacancy);

    void deleteById(Long id);

    Vacancy update(Vacancy vacancy);

    List<Vacancy> findAll();

    Vacancy findById(Long id);
}
