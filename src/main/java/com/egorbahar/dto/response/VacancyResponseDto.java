package com.egorbahar.dto.response;

import com.egorbahar.entity.Department;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class VacancyResponseDto {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private LocalDateTime date;
    @NotNull
    private String position;
    private String department;
}
