package com.egorbahar.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VacancyRequestDto {
    @NotNull
    private String name;
    @NotNull
    private String position;
    @NotNull
    private Long departmentId;
}
