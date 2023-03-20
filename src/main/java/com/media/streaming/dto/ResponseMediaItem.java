package com.media.streaming.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMediaItem {
    private String name;
    private byte[] body;
}
