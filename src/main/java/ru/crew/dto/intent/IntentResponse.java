package ru.crew.dto.intent;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.crew.model.constant.IntentContextType;
import ru.crew.model.constant.IntentStatus;
import ru.crew.model.constant.TimeSlot;

import java.time.Instant;
import java.time.LocalDate;

@Schema(description = "Данные интента (намерения)")
public record IntentResponse(

        @Schema(description = "ID интента", example = "55")
        Long id,

        @Schema(description = "ID пользователя", example = "101")
        Long userId,

        @Schema(description = "Тип контекста", example = "EVENT")
        IntentContextType contextType,

        @Schema(description = "ID события", example = "15")
        Long eventId,

        @Schema(description = "ID места", example = "7")
        Long placeId,

        @Schema(description = "Дата посещения", example = "2025-01-20")
        LocalDate date,

        @Schema(description = "Временной слот", example = "EVENING")
        TimeSlot timeSlot,

        @Schema(description = "Статус намерения", example = "WANT")
        IntentStatus status,

        @Schema(description = "Дата создания", example = "2025-01-10T12:30:00Z")
        Instant createdAt
) {}
