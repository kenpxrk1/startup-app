package ru.crew.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.crew.dto.match.MatchCreateRequest;
import ru.crew.dto.match.MatchResponse;
import ru.crew.event.MatchCreatedEvent;
import ru.crew.mapper.MatchMapper;
import ru.crew.model.IntentEntity;
import ru.crew.model.MatchEntity;
import ru.crew.model.UserEntity;
import ru.crew.repository.IntentRepository;
import ru.crew.repository.MatchRepository;
import ru.crew.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository repository;
    private final MatchMapper mapper;
    private final IntentRepository intentRepository;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;


    public List<MatchResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public MatchResponse findById(Long id) {
        return mapper.toResponse(
                repository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Match not found"))
        );
    }

    @Transactional
    public MatchResponse create(MatchCreateRequest request) {
        UserEntity userA = userRepository.findById(request.userAId())
                .orElseThrow(() -> new RuntimeException("User A not found"));
        UserEntity userB = userRepository.findById(request.userBId())
                .orElseThrow(() -> new RuntimeException("User B not found"));
        IntentEntity intent = intentRepository.findById(request.intentId())
                .orElseThrow(() -> new RuntimeException("Intent not found"));

        MatchEntity entity = mapper.toEntity(request, userA, userB, intent);
        eventPublisher.publishEvent(new MatchCreatedEvent(this, entity));

        return mapper.toResponse(repository.save(entity));
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
