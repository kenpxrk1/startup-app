package ru.crew.mapper;

import org.springframework.stereotype.Component;
import ru.crew.dto.telegram.TelegramUserData;

import java.util.Map;

@Component
public class TelegramUserMapper {

    public TelegramUserData fromMap(Map<String, String> data) {
        return new TelegramUserData(
                Long.parseLong(data.get("user_id")),
                data.get("username"),
                data.get("first_name"),
                data.get("last_name"),
                data.get("language_code")
        );
    }
}
