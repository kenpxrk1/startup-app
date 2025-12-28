package ru.crew.dto.like;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Запрос на создание лайка")
public record LikeCreateRequest(

        @NotNull
        @Schema(description = "Кто лайкнул (user id)", example = "101")
        Long fromUserId,

        @NotNull
        @Schema(description = "Кого лайкнули (user id)", example = "202")
        Long toUserId,

        @NotNull
        @Schema(description = "ID интента, в рамках которого лайк поставлен", example = "55")
        Long intentId
) {}
