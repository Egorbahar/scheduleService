package com.egorbahar.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@NoArgsConstructor
public class DepartmentRequestDto {
    @NotNull
    private String name;
}