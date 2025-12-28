package ru.crew.dto.event;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.crew.model.constant.EventSource;
import ru.crew.model.constant.EventType;

import java.time.Instant;
import java.util.List;

@Schema(description = "Событие")
public record EventResponse(

        @Schema(description = "ID события")
        Long id,

        @Schema(description = "Заголовок события")
        String title,

        @Schema(description = "Описание события")
        String description,

        @Schema(description = "Название места")
        String placeName,

        @Schema(description = "Адрес места проведения")
        String address,

        @Schema(description = "Город проведения")
        String city,

        @Schema(description = "Дата и время начала (UTC)")
        Instant startAt,

        @Schema(description = "Тип события")
        EventType type,

        @Schema(description = "Источник события")
        EventSource source,

        @Schema(description = "Фото события")
        List<String> photos,

        @Schema(description = "Дата создания")
        Instant createdAt,

        @Schema(description = "Дата обновления")
        Instant updatedAt
) {}
