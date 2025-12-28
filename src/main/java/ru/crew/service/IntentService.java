package ru.crew.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.crew.dto.intent.IntentCreateRequest;
import ru.crew.dto.intent.IntentUpdateRequest;
import ru.crew.dto.intent.IntentResponse;
import ru.crew.mapper.IntentMapper;
import ru.crew.model.EventEntity;
import ru.crew.model.IntentEntity;
import ru.crew.model.PlaceEntity;
import ru.crew.model.UserEntity;
import ru.crew.model.constant.IntentContextType;
import ru.crew.repository.EventRepository;
import ru.crew.repository.IntentRepository;
import ru.crew.repository.PlaceRepository;
import ru.crew.repository.UserRepository;
import ru.crew.repository.spec.IntentSpecification;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IntentService {

    private final IntentRepository intentRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final PlaceRepository placeRepository;
    private final IntentMapper mapper;


    @Transactional(readOnly = true)
    public List<IntentResponse> findAll(Long userId, String contextType, Long eventId,
                                        Long placeId, LocalDate date, String status,
                                        int page, int size) {
        Specification<IntentEntity> spec = IntentSpecification.applyFilters(userId, contextType, eventId, placeId, date, status);
        Page<IntentEntity> intents = intentRepository.findAll(spec, PageRequest.of(page, size));
        return intents.map(mapper::toResponse).toList();
    }

    public IntentResponse findById(Long id) {
        IntentEntity entity = intentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Intent not found"));
        return mapper.toResponse(entity);
    }

    @Transactional
    public IntentResponse create(IntentCreateRequest request) {
        UserEntity user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        EventEntity event = null;
        PlaceEntity place = null;
        if (request.contextType() == IntentContextType.EVENT && request.eventId() != null) {
            event = eventRepository.findById(request.eventId())
                    .orElseThrow(() -> new RuntimeException("Event not found"));
        }
        if (request.contextType() == IntentContextType.PLACE && request.placeId() != null) {
            place = placeRepository.findById(request.placeId())
                    .orElseThrow(() -> new RuntimeException("Place not found"));
        }

        IntentEntity entity = mapper.toEntity(request, user, event, place);
        return mapper.toResponse(intentRepository.save(entity));
    }

    @Transactional
    public IntentResponse update(Long id, IntentUpdateRequest request) {
        IntentEntity existing = intentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Intent not found"));

        mapper.updateEntity(request, existing);

        return mapper.toResponse(intentRepository.save(existing));
    }

    @Transactional
    public void delete(Long id) {
        intentRepository.deleteById(id);
    }
}
