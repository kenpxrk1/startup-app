package ru.crew.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.crew.model.EventEntity;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
    Page<EventEntity> findAll(Specification<EventEntity> spec, Pageable pageable);
}

