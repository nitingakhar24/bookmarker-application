package com.bookmarker.app.service;

import com.bookmarker.app.dto.BookmarkDTO;
import com.bookmarker.app.dto.BookmarksDTO;
import com.bookmarker.app.mapper.BookmarkMapper;
import com.bookmarker.app.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    @Transactional(readOnly = true)
    public BookmarksDTO getAllBookmarks(Integer page) {
        int pageNo = page < 1 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "createdAt");
        //Page<BookmarkDTO> bookmarkDTO = bookmarkRepository.findAll(pageable).map(bookmarkMapper::mapToDto);
        Page<BookmarkDTO> bookmarkDTO =  bookmarkRepository.findBookmarks(pageable);
        return new BookmarksDTO(bookmarkDTO);
    }

}
