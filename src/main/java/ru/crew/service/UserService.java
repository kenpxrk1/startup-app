package ru.crew.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.crew.dto.user.UserCreateRequest;
import ru.crew.dto.user.UserResponse;
import ru.crew.dto.user.UserUpdateRequest;
import ru.crew.mapper.UserMapper;
import ru.crew.model.UserEntity;
import ru.crew.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public List<UserResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public UserResponse findById(Long id) {
        UserEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapper.toResponse(entity);
    }

    @Transactional
    public UserResponse create(UserCreateRequest request) {
        UserEntity entity = mapper.toEntity(request);
        UserEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Transactional
    public UserResponse update(Long id, UserUpdateRequest request) {
        UserEntity existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        mapper.update(existing, request);

        UserEntity saved = repository.save(existing);
        return mapper.toResponse(saved);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
