package ru.crew.dto.intent;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import ru.crew.model.constant.IntentStatus;
import ru.crew.model.constant.TimeSlot;

import java.time.LocalDate;

@Schema(description = "Запрос на обновление интента")
public record IntentUpdateRequest(

        @NotNull
        @Schema(description = "Дата посещения", example = "2025-01-20")
        LocalDate date,

        @NotNull
        @Schema(description = "Временной слот", example = "EVENING")
        TimeSlot timeSlot,

        @NotNull
        @Schema(description = "Статус намерения", example = "GO")
        IntentStatus status
) {}
