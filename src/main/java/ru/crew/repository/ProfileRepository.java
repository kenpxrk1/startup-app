package ru.crew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.crew.model.ProfileEntity;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    Optional<ProfileEntity> findByUserId(Long userId);
}
