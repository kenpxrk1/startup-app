package ru.crew.mapper;

import org.springframework.stereotype.Component;
import ru.crew.dto.match.MatchCreateRequest;
import ru.crew.dto.match.MatchResponse;
import ru.crew.model.MatchEntity;
import ru.crew.model.UserEntity;
import ru.crew.model.IntentEntity;

@Component
public class MatchMapper {

    public MatchEntity toEntity(MatchCreateRequest request, UserEntity userA, UserEntity userB, IntentEntity intent) {
        MatchEntity match = new MatchEntity();
        match.setUserA(userA);
        match.setUserB(userB);
        match.setIntent(intent);
        return match;
    }

    public MatchResponse toResponse(MatchEntity entity) {
        return new MatchResponse(
                entity.getId(),
                entity.getUserA().getId(),
                entity.getUserB().getId(),
                entity.getIntent().getId(),
                entity.getCreatedAt()
        );
    }
}
