package site.winesee.project.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import site.winesee.project.springboot.domain.user.Role;

// 초기화하지 않은 @NonNull,final 필드에 대해 생성자를 생성함.
@RequiredArgsConstructor
// Spring Security 설정들을 활성화시킴.
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*
       .csrf().disable().headers().frameOptions().disable()
       h2-console 화면을 사용하기 위해 해당 옵션들을 disable 한다.
         */
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    // authorizeRequests는 URL별 권한 관리를 설정하는 옵션의시작점.
                    // authorizeRequests가 선언되어야지만 antMatchers 옵션을 사용할 수 있다.
                    .authorizeRequests()
                    /*
                     권한 관리 대상을 지정하는 옵션
                     URL,HTTP 메소드별로 관리가 가능하다.
                     "/" 등 지정된 URL들은 permitAll() 옵션을 통해 전체 열람 권한을 줌.
                     "/api/v1/**"주소를 가진 API는 USER 권한을 가진 사람만 가능하도록 함.
                     */
                    .antMatchers("/", "/css/**", "images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    /*
                     설정된 값들 이외 나머지 URL을 나타냄.
                     authenticated()을 추가하면서 나머지 URL들은 모두 인증된 사용자들에게만 허용하게 한다.
                    */
                    .anyRequest().authenticated()
                .and()
                    // 로그아웃 기능에 대한 여러가지 설정의 진입점
                    .logout()
                        // 로그아웃 성공시 / 주소로 이동함.
                        .logoutSuccessUrl("/")
                .and()
                    // OAuth 2 로그인 기능에 대한 여러 설정의 진입점.
                    .oauth2Login()
                        // OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당한다.
                        .userInfoEndpoint()
                            /*
                            소셜 로그인 성공 시 후속 조치르 진행할 UserService 인터페이스의 구현체르 둥록,
                            리소스 서버(즉, 소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자
                            하는 기능을 명시할 수 있습니다.
                             */
                            .userService(customOAuth2UserService);
    }
}

// 변경사항 없음.
