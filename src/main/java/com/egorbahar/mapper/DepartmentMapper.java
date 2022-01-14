package com.egorbahar.mapper;

import com.egorbahar.dto.request.DepartmentRequestDto;
import com.egorbahar.dto.response.DepartmentResponseDto;
import com.egorbahar.entity.Department;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentResponseDto toDepartmentResponseDto(Department department);

    @Mapping(source = "departmentRequestDto.name" , target = "name")
    Department toDepartment(DepartmentRequestDto departmentRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Department department, DepartmentRequestDto departmentRequestDto);

    @IterableMapping(elementTargetType = DepartmentResponseDto.class)
    List<DepartmentResponseDto> toDepartmentResponseDtoList(Collection<Department> departments);
}
