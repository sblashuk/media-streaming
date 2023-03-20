package com.media.streaming.repository;

import com.media.streaming.model.MediaFile;

import java.util.Collection;

public interface MediaRepository {
    Long save(MediaFile file);

    Collection<MediaFile> findAll();

    MediaFile findById(Long id);
}
