package com.media.streaming.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaFile {
    private Long id;
    private byte[] data;

    private String name;

    private String contentType;
}
