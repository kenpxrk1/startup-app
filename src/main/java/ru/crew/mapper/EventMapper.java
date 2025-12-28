package ru.crew.mapper;

import org.springframework.stereotype.Component;
import ru.crew.dto.event.EventCreateRequest;
import ru.crew.dto.event.EventResponse;
import ru.crew.dto.event.EventUpdateRequest;
import ru.crew.model.EventEntity;

@Component
public class EventMapper {

    public EventEntity toEntity(EventCreateRequest r) {
        EventEntity e = new EventEntity();
        e.setTitle(r.title());
        e.setDescription(r.description());
        e.setPlaceName(r.placeName());
        e.setAddress(r.address());
        e.setCity(r.city());
        e.setStartAt(r.startAt());
        e.setType(r.type());
        e.setSource(r.source());
        e.setPhotos(r.photos());
        return e;
    }

    public void updateEntity(EventUpdateRequest r, EventEntity e) {
        if (r.title() != null) e.setTitle(r.title());
        if (r.description() != null) e.setDescription(r.description());
        if (r.placeName() != null) e.setPlaceName(r.placeName());
        if (r.address() != null) e.setAddress(r.address());
        if (r.city() != null) e.setCity(r.city());
        if (r.startAt() != null) e.setStartAt(r.startAt());
        if (r.type() != null) e.setType(r.type());
        if (r.source() != null) e.setSource(r.source());
        if (r.photos() != null) e.setPhotos(r.photos());
    }

    public EventResponse toResponse(EventEntity e) {
        return new EventResponse(
                e.getId(),
                e.getTitle(),
                e.getDescription(),
                e.getPlaceName(),
                e.getAddress(),
                e.getCity(),
                e.getStartAt(),
                e.getType(),
                e.getSource(),
                e.getPhotos(),
                e.getCreatedAt(),
                e.getUpdatedAt()
        );
    }
}

