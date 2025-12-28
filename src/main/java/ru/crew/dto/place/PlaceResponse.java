package ru.crew.dto.place;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.crew.model.constant.PlaceType;

import java.util.List;

@Schema(description = "Ответ с данными места (Place)")
public record PlaceResponse(

        @Schema(description = "Идентификатор места", example = "1")
        Long id,

        @Schema(description = "Название места", example = "Rock Bar Underground")
        String name,

        @Schema(description = "Тип места", example = "BAR")
        PlaceType type,

        @Schema(description = "Город", example = "Moscow")
        String city,

        @Schema(description = "Описание места", example = "Лучший рок-бар в центре города")
        String description,

        @Schema(description = "Ссылки на фотографии места")
        List<String> photos
) {}

