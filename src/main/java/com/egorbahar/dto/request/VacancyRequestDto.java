package com.egorbahar.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VacancyRequestDto {
    @NotNull
    private String name;
    @NotNull
    private String position;
    @NotNull
    private String departmentName;
}
