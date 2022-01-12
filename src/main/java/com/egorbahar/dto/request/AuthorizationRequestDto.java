package com.egorbahar.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AuthorizationRequestDto {
    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;
}
