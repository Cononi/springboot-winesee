package site.winesee.project.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 메인 클래스. 스프링 부트의 자동설정, 스프링Bean 읽기 생성을 모두 자동으로 설정됨
// 이 어노테이션이 설정된 위치부터 읽어가기 때문에 가장 최상단에 위치를 두고 작업을 해야함.
@SpringBootApplication
public class Application {
    public static void  main(String args[]){
        SpringApplication.run(Application.class, args);
    }
}
