package ru.crew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.crew.model.ProfileEntity;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
}
