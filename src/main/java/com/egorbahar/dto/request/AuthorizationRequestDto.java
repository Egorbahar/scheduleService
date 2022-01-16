package com.egorbahar.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AuthorizationRequestDto {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
