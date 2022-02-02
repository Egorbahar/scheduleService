package com.egorbahar.service.impl;

import com.egorbahar.component.LocalMessageSource;
import com.egorbahar.entity.Vacancy;
import com.egorbahar.repository.VacancyRepository;
import com.egorbahar.service.VacancyService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor

public class VacancyServiceImpl implements VacancyService {
    private final VacancyRepository vacancyRepository;
    private final LocalMessageSource messageSource;


    @Override
    public Vacancy save(Vacancy vacancy) {
        return vacancyRepository.save(vacancy);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        findById(id);
        vacancyRepository.deleteById(id);
    }

    @Override
    public Vacancy update(Vacancy vacancy) {
        findById(vacancy.getId());
        return vacancyRepository.saveAndFlush(vacancy);
    }

    @Override
    public List<Vacancy> findAll() {
        return vacancyRepository.findAll();
    }

    @Override
    public Vacancy findById(Long id) {
        return vacancyRepository.findById(id).orElseThrow(()->new RuntimeException(messageSource.getMessage("error.vacancy.notExist", new Object[]{})));
    }

    @Override
    public List<Vacancy> findAllById(List<Long> longList) {
        return vacancyRepository.findAllByIdIn(longList);
    }

}
