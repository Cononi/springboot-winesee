package site.winesee.project.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import site.winesee.project.springboot.service.posts.PostsService;
import site.winesee.project.springboot.web.dto.PostsSaveRequestDto;

/*
final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
 */
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    // 서비스
    private final PostsService postsService;

    // 맵핑 주소
    @PostMapping("/api/v1/posts")
    // @RequestBody json 형식으로 받음.
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return  postsService.save(requestDto);
    }
}
