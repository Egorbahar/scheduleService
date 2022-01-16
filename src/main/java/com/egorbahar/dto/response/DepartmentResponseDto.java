package com.egorbahar.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DepartmentResponseDto {
    private Long id;
    @NotNull
    private String name;
}
