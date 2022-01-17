package com.egorbahar.dto.response;

import com.egorbahar.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PositionResponseDto {
    private String name;
    private String value;
}
