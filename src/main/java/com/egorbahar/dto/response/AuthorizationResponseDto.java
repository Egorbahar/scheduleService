package com.egorbahar.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorizationResponseDto {
    private String token;
    private String refreshToken;
    private String role;
    private Long userId;
}
