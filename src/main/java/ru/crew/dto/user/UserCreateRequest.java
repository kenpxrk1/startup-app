package ru.crew.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Создание пользователя")
public record UserCreateRequest(

        @Schema(description = "Telegram ID пользователя", example = "123456789")
        @NotNull
        Long telegramUserId,

        @Schema(description = "Юзернейм Telegram (без @)", example = "ivan_petrov")
        @Size(max = 255)
        String username,

        @Schema(description = "Имя", example = "Иван")
        @Size(max = 255)
        String firstName,

        @Schema(description = "Фамилия", example = "Петров")
        @Size(max = 255)
        String lastName,

        @Schema(description = "Язык интерфейса", example = "ru")
        @Size(max = 10)
        String language
) {}
