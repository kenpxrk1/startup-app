package ru.crew.repository.spec;

import org.springframework.data.jpa.domain.Specification;
import ru.crew.model.IntentEntity;

import java.time.LocalDate;

public class IntentSpecification {

    public static Specification<IntentEntity> hasUserId(Long userId) {
        return (root, query, cb) -> userId != null ? cb.equal(root.get("user").get("id"), userId) : null;
    }

    public static Specification<IntentEntity> hasContextType(String contextType) {
        return (root, query, cb) -> contextType != null ? cb.equal(root.get("contextType"), contextType) : null;
    }

    public static Specification<IntentEntity> hasEventId(Long eventId) {
        return (root, query, cb) -> eventId != null ? cb.equal(root.get("event").get("id"), eventId) : null;
    }

    public static Specification<IntentEntity> hasPlaceId(Long placeId) {
        return (root, query, cb) -> placeId != null ? cb.equal(root.get("place").get("id"), placeId) : null;
    }

    public static Specification<IntentEntity> hasDate(LocalDate date) {
        return (root, query, cb) -> date != null ? cb.equal(root.get("date"), date) : null;
    }

    public static Specification<IntentEntity> hasStatus(String status) {
        return (root, query, cb) -> status != null ? cb.equal(root.get("status"), status) : null;
    }

    public static Specification<IntentEntity> applyFilters(Long userId, String contextType, Long eventId,
                                                           Long placeId, LocalDate date, String status) {
        return Specification.where(hasUserId(userId))
                .and(hasContextType(contextType))
                .and(hasEventId(eventId))
                .and(hasPlaceId(placeId))
                .and(hasDate(date))
                .and(hasStatus(status));
    }
}

