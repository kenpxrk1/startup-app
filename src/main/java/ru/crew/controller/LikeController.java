package ru.crew.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.crew.dto.like.LikeCreateRequest;
import ru.crew.dto.like.LikeResponse;
import ru.crew.service.LikeService;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
@Tag(name = "Лайки", description = "CRUD операции для лайков")
public class LikeController {

    private final LikeService service;

    @GetMapping
    @Operation(summary = "Получить список всех лайков")
    public List<LikeResponse> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить лайк по ID")
    public LikeResponse get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Создать новый лайк")
    public LikeResponse create(@Valid @RequestBody LikeCreateRequest request) {
        return service.create(request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить лайк по ID")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
