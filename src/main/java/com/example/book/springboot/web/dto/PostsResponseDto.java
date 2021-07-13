package com.example.book.springboot.web.dto;

import com.example.book.springboot.domain.posts.Posts;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PACKAGE)
    public static class All{

        private Long id;
        private String title;
        private String content;
        private String author;
        private LocalDateTime createdDate;
        private LocalDateTime modifiedDate;

        @Builder
        public All(Posts posts){
            this.id = posts.getId();
            this.title = posts.getTitle();
            this.content = posts.getContent();
            this.author = posts.getAuthor();
            this.createdDate = posts.getCreatedDate();
            this.modifiedDate = posts.getModifiedDate();
        }
    }
}
