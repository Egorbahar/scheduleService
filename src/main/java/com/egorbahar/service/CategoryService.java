package com.egorbahar.service;

import com.egorbahar.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    void save(Category category);

    void deleteById(Long id);

    Category update(Category category);

    List<Category> findAll();

    Category findById(Long id);
}
