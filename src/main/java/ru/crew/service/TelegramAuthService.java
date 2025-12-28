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
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramAuthService {

    private final TelegramUserMapper telegramUserMapper;

    @Value("${telegram.bot.token}")
    private String botToken;

    public TelegramUserData verify(String initData) {
        log.info("Telegram auth started");
        log.info("Raw initData: {}", initData);

        Map<String, String> data = parse(initData);
        log.info("Parsed data: {}", data);

        String receivedHash = data.remove("hash");
        if (receivedHash == null) {
            log.error("Hash missing in initData");
            throw new RuntimeException("Hash missing");
        }

        String checkString = data.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("\n"));

        log.info("CheckString:\n{}", checkString);

        byte[] secretKey = buildSecretKey();
        String calculatedHash = hmacSha256Hex(secretKey, checkString);

        log.info("Received hash   : {}", receivedHash);
        log.info("Calculated hash : {}", calculatedHash);

        if (!calculatedHash.equals(receivedHash)) {
            log.error("Telegram signature mismatch");
            throw new RuntimeException("Invalid Telegram signature");
        }

        TelegramUserData user = telegramUserMapper.fromMap(data);
        log.info("Telegram auth success for userId={}", user.id());

        return user;
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

    private byte[] buildSecretKey() {
        log.info("Building secret key using WebAppData + botToken");
        return hmacSha256Bytes("WebAppData", botToken);
    }

    private byte[] hmacSha256Bytes(String key, String data) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            return mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("Error building HMAC bytes", e);
            throw new RuntimeException(e);
        }
    }

    private String hmacSha256Hex(byte[] key, String data) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(key, "HmacSHA256"));
            return Hex.encodeHexString(mac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            log.error("Error calculating HMAC hex", e);
            throw new RuntimeException(e);
        }
    }
}

