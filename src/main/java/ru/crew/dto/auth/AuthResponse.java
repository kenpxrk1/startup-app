package ru.crew.dto.auth;

public record AuthResponse(
   String accessToken,
   String refreshToken
) {}
