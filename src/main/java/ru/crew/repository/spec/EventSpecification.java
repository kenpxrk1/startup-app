package ru.crew.repository.spec;

import org.springframework.data.jpa.domain.Specification;
import ru.crew.model.EventEntity;

import java.time.Instant;

public class EventSpecification {

    public static Specification<EventEntity> hasTitle(String title) {
        return (root, query, cb) -> {
            if (title != null && !title.isEmpty()) {
                return cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
            } else {
                return null;
            }
        };
    }

    public static Specification<EventEntity> hasCity(String city) {
        return (root, query, cb) -> {
            if (city != null && !city.isEmpty()) {
                return cb.equal(cb.lower(root.get("city")), city.toLowerCase());
            } else {
                return null;
            }
        };
    }

    public static Specification<EventEntity> hasStartAfter(Instant startAt) {
        return (root, query, cb) -> startAt != null ? cb.greaterThanOrEqualTo(root.get("startAt"), startAt) : null;
    }

    public static Specification<EventEntity> applyFilters(String title, String city, Instant startAt) {
        return Specification.where(hasTitle(title))
                .and(hasCity(city))
                .and(hasStartAfter(startAt));
    }
}

