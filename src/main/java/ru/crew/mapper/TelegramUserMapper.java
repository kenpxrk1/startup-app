package ru.crew.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import ru.crew.dto.telegram.TelegramUserData;

import java.util.Map;

@Component
public class TelegramUserMapper {

    public TelegramUserData fromMap(Map<String, String> data) {
        String userJson = data.get("user");
        if (userJson == null) {
            throw new RuntimeException("Cannot parse null string (user field missing)");
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode node = mapper.readTree(userJson);
            return new TelegramUserData(
                    node.get("id").asLong(),
                    node.has("username") ? node.get("username").asText() : null,
                    node.has("first_name") ? node.get("first_name").asText() : null,
                    node.has("last_name") ? node.get("last_name").asText() : null,
                    node.has("language_code") ? node.get("language_code").asText() : null
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse user JSON", e);
        }
    }

}
