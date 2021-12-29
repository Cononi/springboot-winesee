package site.winesee.project.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 메인 클래스. 스프링 부트의 자동설정, 스프링Bean 읽기 생성을 모두 자동으로 설정됨
// 이 어노테이션이 설정된 위치부터 읽어가기 때문에 가장 최상단에 위치를 두고 작업을 해야함.

/*
 Spring Data JPA에서 시간에 대해서 자동으로 값을 넣어주는 기능
 도메인을 영속성 컨텍스트에 저장하거나 조회를 수행한 후에 update를 하는 경우
  매번 시간 데이터를 입력하여 주어야 하는데, audit을 이용하면 자동으로 시간을
  매핑하여 데이터베이스의 테이블에 넣어주게 됩니다.
  스프링 부트의 Entry 포인트인 실행 클래스에 @EnableJpaAuditing 어노테이션을 적용하여 JPA Auditing을 활성화 해야하는 것을 잊지 말아야 합니다.
 */
@EnableJpaAuditing // JPA Auditing 활성화

@SpringBootApplication
public class Application {
    public static void  main(String args[]){
        SpringApplication.run(Application.class, args);
    }
}
