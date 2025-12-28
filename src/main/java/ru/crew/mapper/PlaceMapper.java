package ru.crew.mapper;

import org.springframework.stereotype.Component;
import ru.crew.dto.place.PlaceCreateRequest;
import ru.crew.dto.place.PlaceResponse;
import ru.crew.dto.place.PlaceUpdateRequest;
import ru.crew.model.PlaceEntity;

import java.util.ArrayList;

@Component
public class PlaceMapper {

    public PlaceEntity toEntity(PlaceCreateRequest r) {
        PlaceEntity place = new PlaceEntity();
        place.setName(r.name());
        place.setType(r.type());
        place.setCity(r.city());
        place.setDescription(r.description());

        if (r.photos() != null) {
            place.setPhotos(new ArrayList<>(r.photos()));
        } else {
            place.setPhotos(new ArrayList<>());
        }

        return place;
    }

    public void updateEntity(PlaceUpdateRequest r, PlaceEntity place) {
        place.setName(r.name());
        place.setType(r.type());
        place.setCity(r.city());
        place.setDescription(r.description());

        if (r.photos() != null) {
            place.setPhotos(new ArrayList<>(r.photos()));
        }
    }

    public PlaceResponse toResponse(PlaceEntity place) {
        return new PlaceResponse(
                place.getId(),
                place.getName(),
                place.getType(),
                place.getCity(),
                place.getDescription(),
                place.getPhotos()
        );
    }
}


