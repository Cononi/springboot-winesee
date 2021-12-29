package site.winesee.project.springboot.web.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import site.winesee.project.springboot.domain.posts.Posts;
import site.winesee.project.springboot.domain.posts.PostsRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    /*
    Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정
    보통은 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용합니다.
    여러 테스트가 동시에 수행되면 테스트용 데이터베이스인 H2에 데이터가 그대로 남아 있어
    다음 테스트 실행 시 테스트가 실패할 수 있습니다.
     */
    @AfterEach
    public  void  cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void board_get(){
        //given
        String title = "팅테스트 게시글";
        String content = "테스트 본문";
        /*
         테이블에 posts에 insert/update 쿼리를 실행합니다.
         id 값이 있다면 update가 없다면 insert쿼리 실행
         */
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .auther("hong@gmail.com")
                .build());
        // when
        /*
        테이블 posts에 있는 모든 데이터를 조회해오는 메소드 입니다.
         */
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle().equals(title));
        assertThat(posts.getContent().equals(content));
    }

    @Test
    public void BaseTimeEntity_add() {

        // 데이터 입력
        LocalDateTime now = LocalDateTime.of(2021,12,29,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .auther("author")
                .build());
        
        // 데이터 전부 가져오기
        List<Posts> postsList = postsRepository.findAll();

        // 첫번째 데이터 가져오기
        Posts posts = postsList.get(0);
        System.out.println(">>>>>>>>>> createDate=" + posts.getCreateDate());
        System.out.println(">>>>>>>>>> modifiedDate=" + posts.getModifieDate());

        assertThat(posts.getCreateDate()).isAfter(now);
        assertThat(posts.getModifieDate()).isAfter(now);
    }

}
