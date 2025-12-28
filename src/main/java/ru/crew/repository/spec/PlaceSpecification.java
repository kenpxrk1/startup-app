package ru.crew.repository.spec;

import org.springframework.data.jpa.domain.Specification;
import ru.crew.model.PlaceEntity;

public class PlaceSpecification {

    public static Specification<PlaceEntity> hasName(String name) {
        return (root, query, cb) -> name != null && !name.isEmpty() ? cb.like(root.get("name"), name + "%") : null;
    }

    public static Specification<PlaceEntity> hasType(String type) {
        return (root, query, cb) -> type != null ? cb.equal(root.get("type"), type) : null;
    }

    public static Specification<PlaceEntity> hasCity(String city) {
        return (root, query, cb) -> city != null ? cb.equal(root.get("city"), city) : null;
    }

    public static Specification<PlaceEntity> applyFilters(String name, String type, String city) {
        return Specification.where(hasName(name))
                .and(hasType(type))
                .and(hasCity(city));
    }
}

