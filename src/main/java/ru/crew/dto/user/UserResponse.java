package ru.crew.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(description = "Пользователь")
public record UserResponse(

        Long id,

        Long telegramUserId,

        String username,

        String firstName,

        String lastName,

        String language,

        Instant createdAt,

        Instant updatedAt,

        Instant lastActiveAt
) {}

