package ru.crew.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.crew.dto.profile.ProfileCreateRequest;
import ru.crew.dto.profile.ProfileUpdateRequest;
import ru.crew.dto.profile.ProfileResponse;
import ru.crew.service.ProfileService;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
@Tag(name = "Профили", description = "CRUD операции для профилей")
public class ProfileController {

    private final ProfileService service;

    @GetMapping
    @Operation(summary = "Получить список всех профилей")
    public List<ProfileResponse> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить профиль по ID")
    public ProfileResponse get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Создать новый профиль")
    public ProfileResponse create(@Valid @RequestBody ProfileCreateRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить профиль")
    public ProfileResponse update(@PathVariable Long id, @Valid @RequestBody ProfileUpdateRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить профиль по ID")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
