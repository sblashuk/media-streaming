package com.media.streaming.service;

import com.media.streaming.dto.MediaItem;
import com.media.streaming.dto.ResponseMediaItem;
import com.media.streaming.model.MediaFile;
import com.media.streaming.repository.MediaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class MediaService {

    private MediaRepository mediaRepository;

    public void save(MultipartFile file) throws IOException {
        log.info("Save media file: {} with type {}", file.getOriginalFilename(), file.getContentType());
        Long id = mediaRepository.save(MediaFile.builder()
                .name(file.getOriginalFilename())
                .contentType(file.getContentType())
                .data(file.getBytes())
                .build());
        log.info("Media file: {} with type {} and id {} is saved", file.getName(), file.getContentType(), id);
    }

    public Map<String, List<MediaItem>> findAll() {
        return mediaRepository.findAll().stream()
                .map(mediaFile -> new MediaItem(mediaFile.getId(), mediaFile.getName(), mediaFile.getContentType()))
                .collect(Collectors.groupingBy(MediaItem::getType));
    }

    public ResponseMediaItem findDataById(Long id) {
        MediaFile mediaFile = mediaRepository.findById(id);
        return new ResponseMediaItem(mediaFile.getName(), mediaFile.getData());
    }
}
