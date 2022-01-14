package com.egorbahar.service;

import com.egorbahar.entity.Engineer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EngineerService {
    void save(Engineer engineer);

    void deleteById(Long id);

    Engineer update(Engineer engineer);

    List<Engineer> findAll();

    Engineer findById(Long id);
}
