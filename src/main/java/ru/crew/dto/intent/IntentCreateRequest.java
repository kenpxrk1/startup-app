package ru.crew.dto.intent;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import ru.crew.model.constant.IntentContextType;
import ru.crew.model.constant.IntentStatus;
import ru.crew.model.constant.TimeSlot;

import java.time.LocalDate;

@Schema(description = "Запрос на создание интента (намерения)")
public record IntentCreateRequest(

        @NotNull
        @Schema(description = "ID пользователя", example = "101")
        Long userId,

        @NotNull
        @Schema(description = "Тип контекста (EVENT / PLACE)", example = "EVENT")
        IntentContextType contextType,

        @Schema(description = "ID события (если контекст EVENT)", example = "15")
        Long eventId,

        @Schema(description = "ID места (если контекст PLACE)", example = "7")
        Long placeId,

        @NotNull
        @Schema(description = "Дата посещения", example = "2025-01-20")
        LocalDate date,

        @NotNull
        @Schema(description = "Временной слот", example = "EVENING")
        TimeSlot timeSlot,

        @NotNull
        @Schema(description = "Статус намерения", example = "WANT")
        IntentStatus status
) {}
