package ru.crew.mapper;

import org.springframework.stereotype.Component;
import ru.crew.dto.intent.IntentCreateRequest;
import ru.crew.dto.intent.IntentResponse;
import ru.crew.dto.intent.IntentUpdateRequest;
import ru.crew.model.EventEntity;
import ru.crew.model.IntentEntity;
import ru.crew.model.PlaceEntity;
import ru.crew.model.UserEntity;

@Component
public class IntentMapper {

    public IntentEntity toEntity(IntentCreateRequest r, UserEntity user, EventEntity event, PlaceEntity place) {
        IntentEntity intent = new IntentEntity();
        intent.setUser(user);
        intent.setContextType(r.contextType());
        intent.setEvent(event);
        intent.setPlace(place);
        intent.setDate(r.date());
        intent.setTimeSlot(r.timeSlot());
        intent.setStatus(r.status());
        return intent;
    }

    public void updateEntity(IntentUpdateRequest r, IntentEntity intent) {
        intent.setDate(r.date());
        intent.setTimeSlot(r.timeSlot());
        intent.setStatus(r.status());
    }

    public IntentResponse toResponse(IntentEntity intent) {
        return new IntentResponse(
                intent.getId(),
                intent.getUser().getId(),
                intent.getContextType(),
                intent.getEvent() != null ? intent.getEvent().getId() : null,
                intent.getPlace() != null ? intent.getPlace().getId() : null,
                intent.getDate(),
                intent.getTimeSlot(),
                intent.getStatus(),
                intent.getCreatedAt()
        );
    }
}

