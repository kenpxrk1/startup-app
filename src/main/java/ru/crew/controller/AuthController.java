package ru.crew.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.crew.dto.auth.AuthResponse;
import ru.crew.dto.auth.RefreshRequest;
import ru.crew.dto.auth.TelegramAuthRequest;
import ru.crew.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/tg")
    public AuthResponse telegramAuth(@RequestBody TelegramAuthRequest request) {
        return authService.authenticateTelegram(request.initData());
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody RefreshRequest request) {
        return authService.refresh(request.refreshToken());
    }

}
