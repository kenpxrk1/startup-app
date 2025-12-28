package ru.crew.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "токен от телеги")
public record TelegramAuthRequest (
        String initData
) {}
