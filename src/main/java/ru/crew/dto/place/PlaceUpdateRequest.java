package ru.crew.dto.place;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import ru.crew.model.constant.PlaceType;

@Schema(description = "Запрос на обновление места (Place)")
public record PlaceUpdateRequest(

        @NotBlank
        @Size(max = 255)
        @Schema(description = "Название места", example = "Rock Bar Underground")
        String name,

        @NotNull
        @Schema(description = "Тип места", example = "BAR")
        PlaceType type,

        @NotBlank
        @Size(max = 100)
        @Schema(description = "Город", example = "Moscow")
        String city
) {}
