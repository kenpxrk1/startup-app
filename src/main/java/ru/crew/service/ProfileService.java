package ru.crew.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.crew.dto.profile.ProfileCreateRequest;
import ru.crew.dto.profile.ProfileUpdateRequest;
import ru.crew.dto.profile.ProfileResponse;
import ru.crew.mapper.ProfileMapper;
import ru.crew.model.ProfileEntity;
import ru.crew.model.UserEntity;
import ru.crew.repository.ProfileRepository;
import ru.crew.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository repository;
    private final ProfileMapper mapper;
    private final UserRepository userRepository;

    public List<ProfileResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public ProfileResponse findById(Long id) {
        return mapper.toResponse(
                repository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Profile not found"))
        );
    }

    @Transactional
    public ProfileResponse create(ProfileCreateRequest request) {
        UserEntity user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        ProfileEntity entity = mapper.toEntity(request, user);
        return mapper.toResponse(repository.save(entity));
    }

    @Transactional
    public ProfileResponse update(Long id, ProfileUpdateRequest request) {
        ProfileEntity existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        mapper.updateEntity(request, existing);

        return mapper.toResponse(repository.save(existing));
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
