package ru.crew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.crew.model.LikeEntity;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {}

