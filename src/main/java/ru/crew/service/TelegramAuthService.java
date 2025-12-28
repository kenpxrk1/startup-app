package ru.crew.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.crew.dto.telegram.TelegramUserData;
import ru.crew.mapper.TelegramUserMapper;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TelegramAuthService {

    private final TelegramUserMapper telegramUserMapper;

    @Value("${telegram.bot.token}")
    private String botToken;

    public TelegramUserData verify(String initData) {
        Map<String, String> data = parse(initData);
        String receivedHash = data.remove("hash");

        String checkString = data.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("\n"));

        byte[] secretKey = hmacSha256Bytes("WebAppData", botToken);
        String calculatedHash = hmacSha256Hex(secretKey, checkString);

        if (!calculatedHash.equals(receivedHash)) {
            throw new RuntimeException("Invalid Telegram signature");
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

    private byte[] hmacSha256Bytes(String key, String data) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            return mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String hmacSha256Hex(byte[] key, String data) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(key, "HmacSHA256"));
            return Hex.encodeHexString(mac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
