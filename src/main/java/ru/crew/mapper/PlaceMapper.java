package ru.crew.mapper;

import org.springframework.stereotype.Component;
import ru.crew.dto.place.PlaceCreateRequest;
import ru.crew.dto.place.PlaceResponse;
import ru.crew.dto.place.PlaceUpdateRequest;
import ru.crew.model.PlaceEntity;

@Component
public class PlaceMapper {

    public PlaceEntity toEntity(PlaceCreateRequest r) {
        PlaceEntity place = new PlaceEntity();
        place.setName(r.name());
        place.setType(r.type());
        place.setCity(r.city());
        return place;
    }

    public void updateEntity(PlaceUpdateRequest r, PlaceEntity place) {
        place.setName(r.name());
        place.setType(r.type());
        place.setCity(r.city());
    }

    public PlaceResponse toResponse(PlaceEntity place) {
        return new PlaceResponse(
                place.getId(),
                place.getName(),
                place.getType(),
                place.getCity()
        );
    }
}

