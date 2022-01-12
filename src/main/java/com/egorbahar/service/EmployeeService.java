package com.egorbahar.service;

import com.egorbahar.entity.Employee;

import java.util.List;

public interface EmployeeService {
    void save(Employee employee);

    void deleteById(Long id);

    Employee update(Employee employee);

    List<Employee> findAll();

    Employee findById(Long id);
}
