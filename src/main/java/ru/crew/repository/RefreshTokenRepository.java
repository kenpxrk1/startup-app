package ru.crew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.crew.model.RefreshTokenEntity;
import ru.crew.model.UserEntity;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, String> {
    void deleteAllByUser(UserEntity user);
    Optional<RefreshTokenEntity> findByToken(String token);
}
