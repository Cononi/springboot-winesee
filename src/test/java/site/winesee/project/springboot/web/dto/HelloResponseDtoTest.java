package site.winesee.project.springboot.web.dto;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class HelloResponseDtoTest {

    @Test
    public void  lombokTest(){
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name,amount);

        // then
        /*
        assertj라는 테스트 검증 라이브러리 메소드
        검증하고 싶은 대상을 메소드 인자로 받는다.
        메소드 체이닝이 지원되어 isEqualTo와 같이 메소드를 이어서 사용할 수 있다.

        is(equalTo(name))
        assertj의 동등 비교 메소드.
        assertThat에 있는 값과 isEqualTo 값을 비교해서 같을때만 True
         */
        assertThat(dto.getName(), is(equalTo(name)));
        assertThat(dto.getAmount(), is(equalTo(amount)));
    }
}
