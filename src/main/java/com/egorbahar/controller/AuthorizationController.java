package com.egorbahar.controller;

import com.egorbahar.config.jwt.JwtProvider;
import com.egorbahar.dto.request.AuthorizationRequestDto;
import com.egorbahar.dto.request.TokenRefreshRequestDto;
import com.egorbahar.dto.response.AuthorizationResponseDto;
import com.egorbahar.dto.request.RegistrationRequestDto;
import com.egorbahar.dto.response.TokenRefreshResponseDto;
import com.egorbahar.entity.RefreshToken;
import com.egorbahar.entity.User;
import com.egorbahar.exceptions.TokenRefreshException;
import com.egorbahar.service.impl.EmailService;
import com.egorbahar.service.impl.RefreshTokenService;
import com.egorbahar.service.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@AllArgsConstructor
@RequestMapping("/authentication")
public class AuthorizationController {
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final EmailService emailService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequestDto registrationRequestDto) {
        User user = new User();
        user.setPassword(registrationRequestDto.getPassword());
        user.setUsername(registrationRequestDto.getUsername());
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthorizationResponseDto> auth(@RequestBody AuthorizationRequestDto authorizationRequestDto) {
        User user = userService.findByUserNameAndPassword(authorizationRequestDto.getUsername(), authorizationRequestDto.getPassword());
        String token = jwtProvider.generateToken(user.getUsername());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());
        return ResponseEntity.ok(new AuthorizationResponseDto(token,refreshToken.getToken(), user.getRole().getName(), user.getId()));
    }
    @PostMapping("/auth/refreshtoken")
    public ResponseEntity<?> refreshToken(@RequestBody @Valid TokenRefreshRequestDto tokenRefreshRequestDto) {
        String requestRefreshToken = tokenRefreshRequestDto.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtProvider.generateToken(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponseDto(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @RequestMapping(value="/signout", method= RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}