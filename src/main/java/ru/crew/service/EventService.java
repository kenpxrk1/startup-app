package ru.crew.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.crew.dto.event.EventCreateRequest;
import ru.crew.dto.event.EventUpdateRequest;
import ru.crew.dto.event.EventResponse;
import ru.crew.mapper.EventMapper;
import ru.crew.model.EventEntity;
import ru.crew.repository.EventRepository;
import ru.crew.repository.spec.EventSpecification;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository repository;
    private final EventMapper mapper;

    @Transactional(readOnly = true)
    public List<EventResponse> findAll(String title, String city, Instant startAt, int page, int size) {
        Specification<EventEntity> spec = EventSpecification.applyFilters(title, city, startAt);
        Page<EventEntity> events = repository.findAll(spec, PageRequest.of(page, size));
        return events.map(mapper::toResponse).toList();
    }

    public EventResponse findById(Long id) {
        EventEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        return mapper.toResponse(entity);
    }

    @Transactional
    public EventResponse create(EventCreateRequest request) {
        EventEntity entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    @Transactional
    public EventResponse update(Long id, EventUpdateRequest request) {
        EventEntity existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        mapper.updateEntity(request, existing);

        return mapper.toResponse(repository.save(existing));
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
