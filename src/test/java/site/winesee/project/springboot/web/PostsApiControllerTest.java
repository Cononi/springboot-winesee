package site.winesee.project.springboot.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import site.winesee.project.springboot.domain.posts.Posts;
import site.winesee.project.springboot.domain.posts.PostsRepository;
import site.winesee.project.springboot.web.dto.PostsSaveRequestDto;
import site.winesee.project.springboot.web.dto.PostsUpdateRequestDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

        @LocalServerPort
        private int port;

        @Autowired
        private TestRestTemplate restTemplate;

        @Autowired
        private PostsRepository postsRepository;

        @AfterEach
        public  void tearDown() throws Exception {
            postsRepository.deleteAll();
        }

        @Test
        public void posts_set() throws Exception {
            // given
            String title = "title";
            String content = "content";
            PostsSaveRequestDto requestDto = PostsSaveRequestDto
                                            .builder()
                                            .title(title)
                                            .content(content)
                                            .author("author")
                                            .build();
            String url = "http://localhost:" + port + "/api/v1/posts";

            // when
            ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto, Long.class);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(responseEntity.getBody()).isGreaterThan(0L);

            List<Posts> all = postsRepository.findAll();
            assertThat(all.get(0).getTitle()).isEqualTo(title);
            assertThat(all.get(0).getContent()).isEqualTo(content);
        }

        @Test
        public  void Posts_Update() throws  Exception {
            // given
            Posts savedPosts = postsRepository.save(Posts.builder()
                    .title("title")
                    .content("content")
                    .auther("author")
                    .build());

            Long updateId = savedPosts.getId();
            String expectedTitle = "title2";
            String expectedContent = "content2";

            PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                    .title(expectedTitle)
                    .content(expectedContent)
                    .build();

            String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

            HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

            //when
            ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

            //then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(responseEntity.getBody()).isGreaterThan(0L);

            List<Posts> all = postsRepository.findAll();
            assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
            assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
        }

        @Test
        public void posts_delete() throws Exception{
            // given
            Posts savedPosts = postsRepository.save(Posts.builder()
                    .title("타이틀")
                    .content("내용")
                    .auther("작성자")
                    .build());

            // 작성자 아이디 저장
            Long savedPostsId = savedPosts.getId();

            // 연결 주소
            String url = "http://localhost:" + port + "/api/v1/posts/" + savedPostsId;

            // Entity 불러와 저장
            HttpEntity<Posts> saveEntity = new HttpEntity<>(savedPosts);
            // when
            // 응답할 Entity
            ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, saveEntity, Long.class);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(responseEntity.getBody()).isGreaterThan(0L);

            List<Posts> delete = postsRepository.findAll();
            assertThat(delete).isEmpty();
        }
}
