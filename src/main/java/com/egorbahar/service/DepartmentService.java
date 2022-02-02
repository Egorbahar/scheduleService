package com.egorbahar.service;

import com.egorbahar.entity.Department;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DepartmentService {
    Department save(Department department);

    void deleteById(Long id);

    Department update(Department department);

    List<Department> findAll();

    Department findById(Long id);

    Department findByName(String name);
}
