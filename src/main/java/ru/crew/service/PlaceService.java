package ru.crew.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.crew.dto.place.PlaceCreateRequest;
import ru.crew.dto.place.PlaceUpdateRequest;
import ru.crew.dto.place.PlaceResponse;
import ru.crew.mapper.PlaceMapper;
import ru.crew.model.PlaceEntity;
import ru.crew.repository.PlaceRepository;
import ru.crew.repository.spec.PlaceSpecification;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository repository;
    private final PlaceMapper mapper;

    @Transactional(readOnly = true)
    public List<PlaceResponse> findAll(String name, String type, String city, int page, int size) {
        Specification<PlaceEntity> spec = PlaceSpecification.applyFilters(name, type, city);
        Page<PlaceEntity> places = repository.findAll(spec, PageRequest.of(page, size));
        return places.map(mapper::toResponse).toList();
    }

    public PlaceResponse findById(Long id) {
        return mapper.toResponse(
                repository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Place not found"))
        );
    }

    @Transactional
    public PlaceResponse create(PlaceCreateRequest request) {
        PlaceEntity entity = mapper.toEntity(request);
        return mapper.toResponse(repository.save(entity));
    }

    @Transactional
    public PlaceResponse update(Long id, PlaceUpdateRequest request) {
        PlaceEntity existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Place not found"));

        mapper.updateEntity(request, existing);

        return mapper.toResponse(repository.save(existing));
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
