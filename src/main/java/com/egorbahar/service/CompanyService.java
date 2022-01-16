package com.egorbahar.service;

import com.egorbahar.entity.Company;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CompanyService {
    Company save(Company company);

    void deleteById(Long id);

    Company update(Company company);

    List<Company> findAll();

    Company findById(Long id);
}
