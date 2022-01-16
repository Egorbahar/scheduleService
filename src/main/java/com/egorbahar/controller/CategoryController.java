package com.egorbahar.controller;

import com.egorbahar.dto.request.CategoryRequestDto;
import com.egorbahar.dto.response.CategoryResponseDto;
import com.egorbahar.entity.Category;
import com.egorbahar.mapper.CategoryMapper;
import com.egorbahar.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAll() {
        List<CategoryResponseDto> categoryResponseDtoList = categoryMapper.toCategoryResponseDtoList(categoryService.findAll());
        return new ResponseEntity<>(categoryResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getById(@PathVariable("id") @NotBlank @Positive Long id) {
        CategoryResponseDto categoryResponseDto = categoryMapper.toCategoryResponseDto(categoryService.findById(id));
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public void save(@Valid @RequestBody CategoryRequestDto candidateRequestDto) {
        Category category = categoryMapper.toCategory(candidateRequestDto);
        categoryService.save(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> update(@PathVariable("id") @NotBlank @Positive Long id, @Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.toCategory(categoryRequestDto);
        category.setId(id);
        categoryService.update(category);
        CategoryResponseDto categoryResponseDto = categoryMapper.toCategoryResponseDto(category);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }
}
