package com.bookmarker.app.controller;

import com.bookmarker.app.entity.Bookmark;
import com.bookmarker.app.repository.BookmarkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:14-alpine:///demo"
})
class BookmarkControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    private List<Bookmark> bookmarks;

    @BeforeEach
    void setUp() {
        bookmarkRepository.deleteAllInBatch();
        bookmarks = new ArrayList<>();
        bookmarks.add(new Bookmark(null, "Create diagrams", "https://app.diagrams.net/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Microservices Implementation example", "https://javatodev.com/microservices-core-banking-service-implementation/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Google", "https://www.google.com/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Scotia Bank", "https://www.scotiabank.com/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Linkedin", "https://www.linkedin.com/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Spring cloud", "https://spring.io/projects/spring-cloud", Instant.now()));
        bookmarks.add(new Bookmark(null, "Github", "https://github.com/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Quarkus", "https://quarkus.io/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Terraform video tutorial", "https://www.youtube.com/watch?v=aK-Qu_w5ch0", Instant.now()));
        bookmarks.add(new Bookmark(null, "Spring boot", "https://spring.io/projects/spring-boot", Instant.now()));
        bookmarks.add(new Bookmark(null, "Spring Starter", "https://start.spring.io/", Instant.now()));
        bookmarks.add(new Bookmark(null, "Chat GPT", "https://chat.openai.com/", Instant.now()));
        bookmarkRepository.saveAll(bookmarks);
    }

    @ParameterizedTest
    @CsvSource({
            "1,12,2,1,true,false,true,false",
            "2,12,2,2,false,true,false,true"
    })
    void shouldGetBookmarks(int pageNo, int totalElements, int totalPages, int currentPage,
                            boolean isFirst, boolean isLast,
                            boolean hasNext, boolean hasPrevious) throws Exception {
        mockMvc.perform(get("/v1/api/bookmarks?page="+pageNo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(totalElements))
                .andExpect(jsonPath("$.totalPages").value(totalPages))
                .andExpect(jsonPath("$.currentPage").value(currentPage))
                .andExpect(jsonPath("$.isFirst").value(isFirst))
                .andExpect(jsonPath("$.isLast").value(isLast))
                .andExpect(jsonPath("$.hasNext").value(hasNext))
                .andExpect(jsonPath("$.hasPrevious").value(hasPrevious));
    }
}
