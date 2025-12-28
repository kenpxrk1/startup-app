package ru.crew.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.crew.dto.like.LikeCreateRequest;
import ru.crew.dto.like.LikeResponse;
import ru.crew.mapper.LikeMapper;
import ru.crew.model.IntentEntity;
import ru.crew.model.LikeEntity;
import ru.crew.model.UserEntity;
import ru.crew.repository.IntentRepository;
import ru.crew.repository.LikeRepository;
import ru.crew.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository repository;
    private final UserRepository userRepository;
    private final IntentRepository intentRepository;
    private final LikeMapper mapper;

    public List<LikeResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public LikeResponse findById(Long id) {
        return mapper.toResponse(
                repository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Like not found"))
        );
    }

    @Transactional
    public LikeResponse create(LikeCreateRequest request) {
        UserEntity fromUser = userRepository.findById(request.fromUserId())
                .orElseThrow(() -> new RuntimeException("From user not found"));
        UserEntity toUser = userRepository.findById(request.toUserId())
                .orElseThrow(() -> new RuntimeException("To user not found"));
        IntentEntity intent = intentRepository.findById(request.intentId())
                .orElseThrow(() -> new RuntimeException("Intent not found"));

        LikeEntity entity = mapper.toEntity(request, fromUser, toUser, intent);

        return mapper.toResponse(repository.save(entity));
    }


    // Обычно Like не обновляют — можно удалить метод update
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
