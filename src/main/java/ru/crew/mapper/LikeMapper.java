package ru.crew.mapper;

import org.springframework.stereotype.Component;
import ru.crew.dto.like.LikeCreateRequest;
import ru.crew.dto.like.LikeResponse;
import ru.crew.model.IntentEntity;
import ru.crew.model.LikeEntity;
import ru.crew.model.UserEntity;

@Component
public class LikeMapper {

    public LikeEntity toEntity(LikeCreateRequest r, UserEntity fromUser, UserEntity toUser, IntentEntity intent) {
        LikeEntity like = new LikeEntity();
        like.setFromUser(fromUser);
        like.setToUser(toUser);
        like.setIntent(intent);
        return like;
    }

    public LikeResponse toResponse(LikeEntity like) {
        return new LikeResponse(
                like.getId(),
                like.getFromUser().getId(),
                like.getToUser().getId(),
                like.getIntent().getId(),
                like.getCreatedAt()
        );
    }
}
