package com.media.streaming.controller;

import com.media.streaming.dto.MediaItem;
import com.media.streaming.dto.ResponseMediaItem;
import com.media.streaming.service.MediaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/media")
public class MediaController {

    private MediaService mediaService;

    @PostMapping
    public void addMediaFile(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("Save media file: {} with type {}", file.getOriginalFilename(), file.getContentType());
        mediaService.save(file);
    }

    @GetMapping("{id}")
    public ResponseEntity<Resource> getById(@PathVariable("id") Long id) {
        log.info("Get media file by Id: {}", id);
        ResponseMediaItem mediaItem = mediaService.findDataById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Content-Disposition", "attachment; filename=\"%s\"".formatted(mediaItem.getName()))
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new ByteArrayResource(mediaItem.getBody()));

    }

    @GetMapping("/all")
    public Map<String, List<MediaItem>> getAll() {
        log.info("Get all media files");
        return mediaService.findAll();
    }
}
