package ru.crew.dto.telegram;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "данные о пользователе тг")
public record TelegramUserData(
        Long id,
        String username,
        String firstName,
        String lastName,
        String language
) {}
