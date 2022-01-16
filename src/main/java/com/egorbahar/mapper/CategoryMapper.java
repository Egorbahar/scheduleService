package com.egorbahar.mapper;

import com.egorbahar.dto.request.CategoryRequestDto;
import com.egorbahar.dto.response.CategoryResponseDto;
import com.egorbahar.entity.Category;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name" , source = "name")
    @Mapping(target = "duration", source = "duration")
    CategoryResponseDto toCategoryResponseDto(Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name" , source = "name")
    @Mapping(target = "duration", source = "duration")
    Category toCategory(CategoryRequestDto categoryRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Category category, CategoryRequestDto categoryRequestDto);

    @IterableMapping(elementTargetType = CategoryResponseDto.class)
    List<CategoryResponseDto> toCategoryResponseDtoList(Collection<Category> categories);
}
