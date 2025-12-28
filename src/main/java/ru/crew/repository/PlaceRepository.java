package ru.crew.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.crew.model.PlaceEntity;

import java.util.List;

public interface PlaceRepository extends JpaRepository<PlaceEntity, Long> {
    Page<PlaceEntity> findAll(Specification<PlaceEntity> spec, Pageable pageable);

    List<PlaceEntity> findByNameContainingIgnoreCase(String name);

}

