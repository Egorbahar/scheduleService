package com.egorbahar.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TokenRefreshRequestDto {
    @NotEmpty
    private String refreshToken;

}