package com.bookmarker.app.dto;

import com.bookmarker.app.entity.Bookmark;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class BookmarksDTO {
    private List<BookmarkDTO> bookmarks;
    private long totalElements;
    private int totalPages;
    private int currentPage;
    @JsonProperty("isFirst")
    private boolean isFirst;
    @JsonProperty("isLast")
    private boolean isLast;
    private boolean hasNext;
    private boolean hasPrevious;

    public BookmarksDTO(Page<BookmarkDTO> bookmarkPage) {
        this.setBookmarks(bookmarkPage.getContent());
        this.setTotalElements(bookmarkPage.getTotalElements());
        this.setTotalPages(bookmarkPage.getTotalPages());
        this.setCurrentPage(bookmarkPage.getNumber()+1);
        this.setFirst(bookmarkPage.isFirst());
        this.setLast(bookmarkPage.isLast());
        this.setHasNext(bookmarkPage.hasNext());
        this.setHasPrevious(bookmarkPage.hasPrevious());
    }
}
