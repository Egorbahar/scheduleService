package com.egorbahar.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
@NoArgsConstructor
public class DepartmentRequestDto {
    @NotNull
    private String name;
}
