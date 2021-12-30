package site.winesee.project.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.winesee.project.springboot.service.posts.PostsService;
import site.winesee.project.springboot.web.dto.PostsUpdateRequestDto;
import site.winesee.project.springboot.web.dto.PostsResponseDto;
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

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return  postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return  postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}

/*
REST 구성
자원 resource : URI
행윈 verb : HTTP Method
- HTTP Method를 통해 해당 자원에 대한 CRUD Operation을 적용하여 아래와 같이 사용한다.
Create : 데이터 생성 (POST)
Read : 데이터 조회 (GET)
Update : 데이터 수정 (PUT)
Delete : 데이터 삭제 (DELETE)
 */