package ru.crew.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.crew.model.PlaceEntity;

public interface PlaceRepository extends JpaRepository<PlaceEntity, Long> {
    Page<PlaceEntity> findAll(Specification<PlaceEntity> spec, Pageable pageable);
}

