package site.winesee.project.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.winesee.project.springboot.domain.posts.Posts;
import site.winesee.project.springboot.domain.posts.PostsRepository;
import site.winesee.project.springboot.web.dto.PostsUpdateRequestDto;
import site.winesee.project.springboot.web.dto.PostsResponseDto;
import site.winesee.project.springboot.web.dto.PostsSaveRequestDto;

// final이 선언된 모든 필드를 인자갑스로 하는 생성자.
@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }


    /*
    update 기능에서 데이터 베이스에 쿼리를 날리는 부분이 없다.
    이게 가능한 이유는 JPA의 영속성 컨텍스트 때문이다.
    영속성 컨텍스트란, 엔티티를 영구 저장하는 환경입니다. 일종의 논리적 개념이라고 보시면 되며, JPA의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐
    로 갈립니다.
    JAP의 엔티티 매니저가 활성화된 상태로 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태입니다.
    이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영합니다. Entity 객체의 값만 변경하면 별도로
    Update 쿼리를 날릴 필요가 없다. 이 개념을 더티 체킹이라 한다.
     */
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }
}
