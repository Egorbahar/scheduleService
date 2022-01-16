package com.egorbahar.service.impl;

import com.egorbahar.component.LocalMessageSource;
import com.egorbahar.entity.Category;
import com.egorbahar.repository.CategoryRepository;
import com.egorbahar.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final LocalMessageSource messageSource;
    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(Category category) {
        findById(category.getId());
        return categoryRepository.saveAndFlush(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException(messageSource.getMessage("error.category.notExist", new Object[]{})));
    }
}
