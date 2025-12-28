package ru.crew.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.crew.dto.event.EventCreateRequest;
import ru.crew.dto.event.EventUpdateRequest;
import ru.crew.dto.event.EventResponse;
import ru.crew.service.EventService;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@Tag(name = "События", description = "CRUD операции для событий")
public class EventController {

    private final EventService service;


    @GetMapping
    @Operation(summary = "Получить список событий с фильтром и пагинацией")
    public List<EventResponse> getAll(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Instant startAt,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.findAll(title, city, startAt, page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить событие по ID")
    public EventResponse get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Создать новое событие")
    public EventResponse create(@Valid @RequestBody EventCreateRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить существующее событие")
    public EventResponse update(@PathVariable Long id, @Valid @RequestBody EventUpdateRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить событие по ID")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
