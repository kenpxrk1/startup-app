package ru.crew.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.crew.model.IntentEntity;

public interface IntentRepository extends JpaRepository<IntentEntity, Long> {
    Page<IntentEntity> findAll(Specification<IntentEntity> spec, Pageable pageable);
}

