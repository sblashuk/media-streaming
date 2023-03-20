package com.media.streaming.repository;

import com.media.streaming.model.MediaFile;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class InMemoryMediaRepository implements MediaRepository {

    private AtomicLong ids = new AtomicLong(0);
    private Map<Long, MediaFile> storage = new HashMap<>();

    @Override
    public Long save(MediaFile mediaFile) {
        mediaFile.setId(ids.incrementAndGet());
        storage.put(mediaFile.getId(), mediaFile);
        return mediaFile.getId();
    }

    @Override
    public Collection<MediaFile> findAll() {
        return storage.values();
    }

    @Override
    public MediaFile findById(Long id) {
        return storage.get(id);
    }
}
