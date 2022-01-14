package com.egorbahar.service.impl;

import com.egorbahar.component.LocalMessageSource;
import com.egorbahar.entity.Department;
import com.egorbahar.repository.DepartmentRepository;
import com.egorbahar.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final LocalMessageSource messageSource;

    @Override
    public void save(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        departmentRepository.deleteById(id);
    }

    @Override
    public Department update(Department department) {
        findById(department.getId());
        return departmentRepository.saveAndFlush(department);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new RuntimeException(messageSource.getMessage("error.department.notExist", new Object[]{})));
    }
}
