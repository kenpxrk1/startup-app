package ru.crew.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.crew.dto.telegram.TelegramUserData;
import ru.crew.mapper.TelegramUserMapper;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TelegramAuthService {

    private final TelegramUserMapper telegramUserMapper;

    @Value("${telegram.bot.token}")
    private String botToken;

    public TelegramUserData verify(String initData) {
        log.info("InitData = '{}'", initData);
        Map<String, String> data = parse(initData);
        log.info(data.toString());

        String receivedHash = data.remove("hash");
        log.info(receivedHash);

        // Проверка подписи
        String dataCheckString = data.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("\n"));

        try {
            byte[] secretKey = sha256(botToken);
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secretKey, "HmacSHA256"));
            String calculated = Hex.encodeHexString(mac.doFinal(dataCheckString.getBytes(StandardCharsets.UTF_8)));

            if (!calculated.equalsIgnoreCase(receivedHash)) {
                throw new RuntimeException("Invalid Telegram signature");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error verifying Telegram signature", e);
        }

        // Проверка наличия user
        String userJson = data.get("user");
        if (userJson == null || userJson.isEmpty()) {
            throw new RuntimeException("Cannot parse null string (user field is missing)");
        }

        return telegramUserMapper.fromMap(data);
    }


    private Map<String, String> parse(String initData) {
        Map<String, String> result = new HashMap<>();

        for (String pair : initData.split("&")) {
            int idx = pair.indexOf('=');
            if (idx == -1) continue;

            String key = URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8);
            String value = URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8);

            result.put(key, value);
        }

        return result;
    }

    private byte[] sha256(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(value.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
