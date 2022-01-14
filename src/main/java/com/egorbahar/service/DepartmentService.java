package com.egorbahar.service;

import com.egorbahar.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DepartmentService {
    void save(Department department);

    void deleteById(Long id);

    Department update(Department department);

    List<Department> findAll();

    Department findById(Long id);

    Department findByName(String name);
}
