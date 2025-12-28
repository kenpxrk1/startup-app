package ru.crew.dto.like;


import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(description = "Данные о лайке")
public record LikeResponse(

        @Schema(description = "ID лайка", example = "5001")
        Long id,

        @Schema(description = "Кто лайкнул", example = "101")
        Long fromUserId,

        @Schema(description = "Кого лайкнули", example = "202")
        Long toUserId,

        @Schema(description = "ID интента", example = "55")
        Long intentId,

        @Schema(description = "Дата и время создания", example = "2025-01-11T10:10:00Z")
        Instant createdAt
) {}
