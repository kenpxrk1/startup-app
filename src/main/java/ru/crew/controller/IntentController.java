package ru.crew.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.crew.dto.intent.IntentCreateRequest;
import ru.crew.dto.intent.IntentUpdateRequest;
import ru.crew.dto.intent.IntentResponse;
import ru.crew.service.IntentService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/intents")
@RequiredArgsConstructor
@Tag(name = "Интенты", description = "CRUD операции для интентов")
public class IntentController {

    private final IntentService service;

    @GetMapping
    @Operation(summary = "Получить список интентов с фильтром и пагинацией")
    public List<IntentResponse> getAll(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String contextType,
            @RequestParam(required = false) Long eventId,
            @RequestParam(required = false) Long placeId,
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return service.findAll(userId, contextType, eventId, placeId, date, status, page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить интент по ID")
    public IntentResponse get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Создать новый интент")
    public IntentResponse create(@Valid @RequestBody IntentCreateRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить интент")
    public IntentResponse update(@PathVariable Long id, @Valid @RequestBody IntentUpdateRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить интент по ID")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

