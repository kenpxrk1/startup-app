package ru.crew.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.crew.dto.place.PlaceCreateRequest;
import ru.crew.dto.place.PlaceUpdateRequest;
import ru.crew.dto.place.PlaceResponse;
import ru.crew.service.PlaceService;

import java.util.List;

@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor
@Tag(name = "Места", description = "CRUD операции для мест")
public class PlaceController {

    private final PlaceService service;


    @GetMapping
    @Operation(summary = "Получить список мест с фильтром и пагинацией")
    public List<PlaceResponse> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String city,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.findAll(name, type, city, page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить место по ID")
    public PlaceResponse get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Создать новое место")
    public PlaceResponse create(@Valid @RequestBody PlaceCreateRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить место")
    public PlaceResponse update(@PathVariable Long id, @Valid @RequestBody PlaceUpdateRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить место по ID")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
