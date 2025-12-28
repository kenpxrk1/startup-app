package ru.crew.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.crew.dto.auth.AuthResponse;
import ru.crew.dto.telegram.TelegramUserData;
import ru.crew.model.RefreshTokenEntity;
import ru.crew.model.UserEntity;
import ru.crew.repository.RefreshTokenRepository;
import ru.crew.repository.UserRepository;
import ru.crew.mapper.UserMapper;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TelegramAuthService telegramAuthService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;

    @Value("${jwt.refresh-token-ttl}")
    private long refreshTtl;

    @Transactional
    public AuthResponse authenticateTelegram(String initData) {
        TelegramUserData tgUser = telegramAuthService.verify(initData);

        UserEntity user = userRepository
                .findById(tgUser.id())
                .orElseGet(() -> createUser(tgUser));

        refreshTokenRepository.deleteAllByUser(user);

        return generateAndSaveTokens(user);
    }

    @Transactional
    public AuthResponse refresh(String refreshToken) {
        if (!jwtService.isValid(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }

        RefreshTokenEntity tokenEntity = refreshTokenRepository
                .findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));

        if (tokenEntity.getExpiresAt().isBefore(Instant.now())) {
            refreshTokenRepository.delete(tokenEntity);
            throw new RuntimeException("Refresh token expired");
        }

        UserEntity user = tokenEntity.getUser();

        refreshTokenRepository.delete(tokenEntity);

        return generateAndSaveTokens(user);
    }

    private AuthResponse generateAndSaveTokens(UserEntity user) {
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        RefreshTokenEntity entity = new RefreshTokenEntity(
                refreshToken,
                user,
                Instant.now().plus(refreshTtl, ChronoUnit.MILLIS)
        );

        refreshTokenRepository.save(entity);

        return new AuthResponse(accessToken, refreshToken);
    }

    private UserEntity createUser(TelegramUserData tgUser) {
        UserEntity user = userMapper.fromTelegramUserData(tgUser);
        return userRepository.save(user);
    }
}