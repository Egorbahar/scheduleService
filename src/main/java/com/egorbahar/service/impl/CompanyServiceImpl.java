package com.egorbahar.service.impl;

import com.egorbahar.component.LocalMessageSource;
import com.egorbahar.entity.Company;
import com.egorbahar.repository.CompanyRepository;
import com.egorbahar.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final LocalMessageSource messageSource;

    @Override
    public void save(Company company) {
        companyRepository.save(company);
    }

    @Override
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Company update(Company company) {
        findById(company.getId());
        return companyRepository.saveAndFlush(company);
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company findById(Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new RuntimeException(messageSource.getMessage("error.company.notExist", new Object[]{})));
    }
}
