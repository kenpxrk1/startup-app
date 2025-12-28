package ru.crew.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.crew.service.MediaService;

import java.util.List;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
@Tag(name = "Медиа", description = "Загрузка и получение файлов")
public class MediaController {

    private final MediaService mediaService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузить файл", description = "Принимает один файл и сохраняет его в MinIO")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file) throws Exception {
        String url = mediaService.uploadFile(file);
        return ResponseEntity.ok(url);
    }

    @PostMapping(value = "/upload/multiple", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузить несколько файлов", description = "Принимает несколько файлов и возвращает ссылки на них")
    public ResponseEntity<List<String>> uploadMultipleFiles(
            @RequestParam("files") MultipartFile[] files) throws Exception {
        List<String> urls = mediaService.uploadMultipleFiles(files);
        return ResponseEntity.ok(urls);
    }


    @GetMapping("/download/{filename}")
    @Operation(summary = "Скачать файл", description = "Возвращает файл по имени")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws Exception {
        Resource resource = mediaService.downloadFile(filename);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }
}


