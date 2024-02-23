package com.bookmarker.app.mapper;

import com.bookmarker.app.dto.BookmarkDTO;
import com.bookmarker.app.entity.Bookmark;
import org.springframework.stereotype.Component;

@Component
public class BookmarkMapper {
    public BookmarkDTO mapToDto(Bookmark bookmark) {
        return BookmarkDTO.builder().url(bookmark.getUrl())
                .title(bookmark.getTitle())
                .bookMarkId(bookmark.getBookMarkId())
                .createdAt(bookmark.getCreatedAt())
                .build();
    }
}
