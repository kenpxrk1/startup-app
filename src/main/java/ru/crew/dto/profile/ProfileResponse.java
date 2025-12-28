package ru.crew.dto.profile;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.List;

@Schema(description = "Профиль пользователя")
public record ProfileResponse(

        @Schema(description = "ID профиля")
        Long id,

        @Schema(description = "ID пользователя")
        Long userId,

        @Schema(description = "Имя")
        String name,

        @Schema(description = "Биография")
        String bio,

        @Schema(description = "Возраст")
        Integer age,

        @Schema(description = "Город")
        String city,

        @Schema(description = "Интересы")
        List<String> interests,

        @Schema(description = "Фото (URL)")
        List<String> photos,

        @Schema(description = "Видимость намерений")
        Boolean showIntents,

        @Schema(description = "Дата создания")
        Instant createdAt,

        @Schema(description = "Дата обновления")
        Instant updatedAt
) {}

