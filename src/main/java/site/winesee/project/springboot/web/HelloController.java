package site.winesee.project.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.winesee.project.springboot.web.dto.HelloResponseDto;

// 컨트롤러를 json 형식으로 반환하도록 만들어준다.
// @Controller와 @ResponseBody를 합친것으로 메소드마다 선언하던 @ResponseBody를 한번에 자동설정 해준다.
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    // HTTP 메소드인 GET요청을 받을수 있는 API
    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                        @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}
