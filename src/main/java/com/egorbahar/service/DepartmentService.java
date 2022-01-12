package com.egorbahar.service;

import com.egorbahar.entity.Department;

import java.util.List;

public interface DepartmentService {
    void save(Department department);

    void deleteById(Long id);

    Department update(Department department);

    List<Department> findAll();

    Department findById(Long id);
}
