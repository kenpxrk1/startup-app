package ru.crew.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.crew.model.MatchEntity;

public interface MatchRepository extends JpaRepository<MatchEntity, Long> {}

