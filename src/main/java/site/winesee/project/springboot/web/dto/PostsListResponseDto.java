package site.winesee.project.springboot.web.dto;

import lombok.Getter;
import site.winesee.project.springboot.domain.posts.Posts;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    // 생성자 주입
    public PostsListResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getTitle();
        this.modifiedDate = entity.getModifieDate();
    }
}
