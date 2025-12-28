package ru.crew.mapper;

import org.springframework.stereotype.Component;
import ru.crew.dto.telegram.TelegramUserData;
import ru.crew.dto.user.UserCreateRequest;
import ru.crew.dto.user.UserResponse;
import ru.crew.dto.user.UserUpdateRequest;
import ru.crew.model.UserEntity;

import java.time.Instant;

@Component
public class UserMapper {

    public UserEntity toEntity(UserCreateRequest request) {
        UserEntity user = new UserEntity();
        user.setTelegramUserId(request.telegramUserId());
        user.setUsername(request.username());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setLanguage(request.language());
        user.setLastActiveAt(Instant.now());
        return user;
    }

    public void update(UserEntity user, UserUpdateRequest request) {
        user.setUsername(request.username());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setLanguage(request.language());
    }

    public UserResponse toResponse(UserEntity user) {
        return new UserResponse(
                user.getId(),
                user.getTelegramUserId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getLanguage(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getLastActiveAt()
        );
    }

    public UserEntity fromTelegramUserData(TelegramUserData tgUser) {
        UserEntity user = new UserEntity();
        user.setTelegramUserId(tgUser.id());
        user.setUsername(tgUser.username());
        user.setFirstName(tgUser.firstName());
        user.setLastName(tgUser.lastName());
        user.setLanguage(tgUser.language());
        return user;
    }
}
