package com.media.streaming.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaItem {
    private Long id;
    private String name;
    private String type;
}
