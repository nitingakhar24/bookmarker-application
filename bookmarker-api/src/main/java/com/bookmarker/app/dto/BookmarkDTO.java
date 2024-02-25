package com.bookmarker.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@Builder
public class BookmarkDTO {
    private Long bookMarkId;
    private String title;
    private String url;
    private Instant createdAt;
}
