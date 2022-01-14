package com.egorbahar.mapper;

import com.egorbahar.dto.request.CompanyRequestDto;
import com.egorbahar.dto.response.CompanyResponseDto;
import com.egorbahar.entity.Company;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyResponseDto toCompanyResponseDto(Company company);

    @Mapping(target = "id", ignore = true)
    Company toCompany(CompanyRequestDto companyRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Company company, CompanyRequestDto companyRequestDto);

    @IterableMapping(elementTargetType = CompanyResponseDto.class)
    List<CompanyResponseDto> toCompanyResponseDtoList(Collection<Company> companies);
}
