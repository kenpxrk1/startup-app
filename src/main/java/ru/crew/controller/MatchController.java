package ru.crew.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.crew.dto.match.MatchCreateRequest;
import ru.crew.dto.match.MatchResponse;
import ru.crew.service.MatchService;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
@Tag(name = "Матчи", description = "CRUD операции для матчей")
public class MatchController {

    private final MatchService service;

    @GetMapping
    @Operation(summary = "Получить список всех матчей")
    public List<MatchResponse> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить матч по ID")
    public MatchResponse get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Создать новый матч")
    public MatchResponse create(@Valid @RequestBody MatchCreateRequest request) {
        return service.create(request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить матч по ID")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
