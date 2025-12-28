package ru.crew.dto.match;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Запрос на создание матча (Match)")
public record MatchCreateRequest(

        @NotNull
        @Schema(description = "ID пользователя A")
        Long userAId,

        @NotNull
        @Schema(description = "ID пользователя B")
        Long userBId,

        @NotNull
        @Schema(description = "ID интента, на основе которого произошёл матч")
        Long intentId
) {}
