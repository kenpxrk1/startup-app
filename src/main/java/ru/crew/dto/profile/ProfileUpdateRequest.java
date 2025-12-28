package ru.crew.dto.profile;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.List;

@Schema(description = "Запрос на обновление профиля пользователя")
public record ProfileUpdateRequest(

        @Schema(description = "Отображаемое имя пользователя", example = "Иван")
        @Size(max = 255)
        String name,

        @Schema(description = "Биография/о себе", example = "Люблю путешествовать и кофе")
        @Size(max = 500)
        String bio,

        @Schema(description = "Возраст пользователя", example = "26")
        @Min(18)
        @Max(120)
        Integer age,

        @Schema(description = "Город проживания", example = "Москва")
        @Size(max = 255)
        String city,

        @Schema(description = "Интересы пользователя (теги)", example = "[\"музыка\",\"спорт\"]")
        List<@Size(max = 50) String> interests,

        @Schema(description = "Ссылки на фото (S3/CDN)", example = "[\"https://cdn/app/photo1.jpg\"]")
        List<@Size(max = 2048) String> photos,

        @Schema(description = "Видимость намерений", example = "false")
        Boolean showIntents
) {}

