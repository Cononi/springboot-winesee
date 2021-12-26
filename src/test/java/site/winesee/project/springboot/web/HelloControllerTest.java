package site.winesee.project.springboot.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;

/*
MVC를 위한 테스트.
웹에서 테스트하기 힘든 컨트롤러를 테스트하는 데 적합.
웹상에서 요청과 응답에 대해 테스트할 수 있음.
시큐리티, 필터까지 자동으로 테스트하며, 수동으로 추가/삭제 가능.
@SpringBootTest 어노테이션보다 가볍게 테스트할 수 있음.
다음과 같은 내용만 스캔하도록 제한함.
@Controller, @ControllerAdvice, @JsonComponent, Converter, GenericConverter, Filter, HandlerInterceptor
 */
@WebMvcTest
//  확장기능 구현하기 junit5에서는 대부분이 이기능을 통해 지원됨
@ExtendWith(SpringExtension.class)
public class HelloControllerTest {

    // 스프링이 관리하는 bean을 주입
    @Autowired
    /*
       웹 API를 테스트할 때 사용합니다.
       스프링 MVC 테스트의 시작점 입니다.
       이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있습니다.
    * */
    private MockMvc mvc;

    @Test
    public void hello() throws Exception {
        String hello = "hello";

        /* mvc.perform - MockMvc를 통해 /hello 주소로 HTTP GET 요청을 합니다.
        *  체이닝이 지원되서 아래와 같이 여러 검증 기능을 이어서 선언할 수 있습니다.
        * .andExpect(status.isOk()) - mvc.perform의 결과를 HTTP Header의 status를 검증합니다.
        * 200,404,500등의 상태 검증
        * .andExpect(content().string(hello))
        * mvc.perform의 결과를 검증합니다.
        * 응답 본문의 내용을 검증합니다.
        * Controller에서 "hello"를 리터하기 때문에 값이 맞는지 검증합니다.
         * */
        mvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(hello));
    }

    @Test
    public  void helloDto() throws Exception {
        String name = "hello";
        int amount = 1000;
        /*
        API 테스트할 때 사용할 요청 파라메터를 설정합니다.
        단, 값은 String만 허용
        숫자/날짜를 등록할때는 문자열로 형변환을 해줘야 한다.

        jsonpath json응답값을 필드별로 검증할 수 있는 메소드.
        $기준으로 필드명을 명시함
         */
        mvc.perform(MockMvcRequestBuilders.get("/hello/dto")
                    .param("name",name).param("amount",String.valueOf(amount)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    }


}
