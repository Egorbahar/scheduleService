package com.egorbahar.service;

import com.egorbahar.entity.Company;

import java.util.List;

public interface CompanyService {
    void save(Company company);

    void deleteById(Long id);

    Company update(Company company);

    List<Company> findAll();

    Company findById(Long id);
}
