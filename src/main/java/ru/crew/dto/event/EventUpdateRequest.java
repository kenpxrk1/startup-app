package ru.crew.dto.event;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import ru.crew.model.constant.EventSource;
import ru.crew.model.constant.EventType;

import java.time.Instant;
import java.util.List;

@Schema(description = "Запрос на обновление события")
public record EventUpdateRequest(

        @Schema(description = "Заголовок события", example = "Концерт Muse")
        @Size(max = 255)
        String title,

        @Schema(description = "Описание события", example = "Лучший концерт года")
        @Size(max = 2000)
        String description,

        @Schema(description = "Название места", example = "Ледовый дворец")
        String placeName,

        @Schema(description = "Адрес проведения", example = "ул. Ленина, 10")
        String address,

        @Schema(description = "Город", example = "Санкт-Петербург")
        String city,

        @Schema(description = "Дата и время начала (UTC)")
        Instant startAt,

        @Schema(description = "Тип события", example = "CONCERT")
        EventType type,

        @Schema(description = "Источник события", example = "EXTERNAL")
        EventSource source,

        @Schema(description = "Фото события", example = "[\"https://cdn/app/event.jpg\"]")
        List<@Size(max = 2048) String> photos
) {}

