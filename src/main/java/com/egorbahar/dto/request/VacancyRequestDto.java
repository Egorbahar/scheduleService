package com.egorbahar.dto.request;

import com.egorbahar.entity.Department;
import com.egorbahar.enums.Position;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class VacancyRequestDto {
    @NotNull
    private String name;
    @NotNull
    private Date date;
    @NotNull
    private Position position;
    @NotNull
    private Department department;
}
