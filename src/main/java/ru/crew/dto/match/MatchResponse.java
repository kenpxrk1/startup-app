package ru.crew.dto.match;


import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.UUID;

@Schema(description = "Ответ с данными матча (Match)")
public record MatchResponse(

        @Schema(description = "ID матча")
        Long id,

        @Schema(description = "ID пользователя A")
        Long userAId,

        @Schema(description = "ID пользователя B")
        Long userBId,

        @Schema(description = "ID интента")
        Long intentId,

        @Schema(description = "Дата и время создания", example = "2025-01-10T12:30:00Z")
        Instant createdAt
) {}
